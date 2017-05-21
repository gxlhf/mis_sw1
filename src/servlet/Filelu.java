package servlet;

import readoffice.docConvector;
import readoffice.PDF2SWFUtil;
import readoffice.JPG2PdfUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;  
import org.apache.poi.hssf.extractor.ExcelExtractor;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
import org.apache.poi.hslf.extractor.PowerPointExtractor;  
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;  
import org.apache.poi.xslf.usermodel.XMLSlideShow;  

/**
 * Servlet implementation class Filelu
 */
@WebServlet("/Filelu")
public class Filelu extends HttpServlet {
	@SuppressWarnings("unused")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  
        response.setContentType("text/html;charset=utf-8"); 
		// 获取请求参数： 区分不同的操作类型
		String method = request.getParameter("method");
		if ("upload".equals(method)) {
			// 上传
			upload(request,response);
		}
		
		else if ("downList".equals(method)) {
			// 进入下载列表
			downList(request,response);
		}
		
		else if ("down".equals(method)) {
			// 下载
			down(request,response);
		}
		else if("search".equals(method)){
			//查找关键字
			search(request,response);
		}
		else if("delete".equals(method)){
			//删除
			deletefile(request,response);
		}
		else if("plurality".equals(method)){
			//批量删除
			pluralityfile(request,response);
		}
		else if("converter".equals(method)){
			//预览
			String exePath = "D:/Files/OpenOfficeSwfTools/SwfTools/pdf2swf.exe";
			PrintWriter out=response.getWriter();
			String fileName = request.getParameter("fileName");
			String basePath = getServletContext().getRealPath("/upload");
		//	String changePath=getServletContext().getRealPath("/preview");
			String bpath=basePath+"\\"+fileName;
			System.out.println(bpath);
			String suffix=fileName.substring(fileName.lastIndexOf('.')+1);   //根据不同的后缀采取不同的转换策略
			if(suffix.equals("txt")||suffix.equals("html")||suffix.equals("xml")||suffix.equals("doc")||suffix.equals("doc")||
					suffix.equals("ppt")||suffix.equals("pptx")||suffix.equals("xls")||suffix.equals("xlsx")){
			docConvector d=new docConvector(bpath);
	        d.conver();                //转换结束
			}
			else if(suffix.equals("pdf")){
				try {
					
		            PDF2SWFUtil.pdf2swf(bpath, exePath);
		        } catch (IOException e) {
		            System.err.println("转换出错！");
		            e.printStackTrace();
		        }
			}
			else if(suffix.equals("jpg")||suffix.equals("jpeg")||suffix.equals("png")){
				String picname=fileName.replace(suffix,"pdf");              //为了将图片格式转成pdf
				String cpath=basePath+"\\"+picname;
				JPG2PdfUtil pt=new JPG2PdfUtil(bpath,cpath);
		        pt.imgtopdf();
                try {				
		            PDF2SWFUtil.pdf2swf(cpath, exePath);
		        } catch (IOException e) {
		            System.err.println("转换出错！");
		            e.printStackTrace();
		        }
                File fff=new File(cpath);                   //删除产生的pdf
                fff.delete();
			}
		//	else{
		//		 request.setAttribute("123", "flaag");
		//		 request.getRequestDispatcher("/showswf.jsp").forward(request, response);  
		//	}
	        String changepath = getServletContext().getRealPath("/preview");
	        fileName=fileName.substring(0,fileName.lastIndexOf('.'));   //提取名字
	        fileName=fileName.replace('#', '_');          //替换，因为swffile无法识别特殊字符
	        fileName="preview/"+fileName+".swf";             //swffile只能读取本项目目录里的文件，因此只需要将文件名传过去就行了
	        request.setAttribute("swfpath", fileName);
	     //   out.print("<script>location.href='/F0Fproject/showswf.jsp';</script>");
	        request.getRequestDispatcher("/showswf.jsp").forward(request, response);  
		}
	}
	
	
	/**
	 * 1. 上传
	 */
	private void upload(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			// 1. 创建工厂对象
			FileItemFactory factory = new DiskFileItemFactory();
			// 2. 文件上传核心工具类
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置大小限制参数
			upload.setFileSizeMax(10*1024*1024);	// 单个文件大小限制
			upload.setSizeMax(50*1024*1024);		// 总文件大小限制
			upload.setHeaderEncoding("UTF-8");		// 对中文文件编码处理
			

			// 判断
			if (upload.isMultipartContent(request)) {
				// 3. 把请求数据转换为list集合
				List<FileItem> list = upload.parseRequest(request);
				// 遍历
				for (FileItem item : list){
					// 判断：普通文本数据
					if (item.isFormField()){
						// 获取名称
						String name = item.getFieldName();
						// 获取值
						String value = item.getString();
				//		System.out.println(value);
					} 
					// 文件表单项
					else {
						/******** 文件上传 ***********/
						// a. 获取文件名称
						String name = item.getName();
						// ----处理上传文件名重名问题----
						// a1. 先得到唯一标记
						String id = UUID.randomUUID().toString();
						//a1.5 再获得上传时间
					//	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd HH-mm-ss");
					//	String strr=sdf.format(new Date());
						// a2. 拼接文件名
						name=id + "#" + name;	
					//	name = strr+"@"+id + "#" + name;						
						
						// b. 得到上传目录
						String basePath = getServletContext().getRealPath("/upload");
						// c. 创建要上传的文件对象
						File file = new File(basePath,name);
						// d. 上传
						item.write(file);
						item.delete();  // 删除组件运行时产生的临时文件
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print("<script>alert('上传成功');location.href='/MIS/UploadFile.jsp';</script>");	
		
	}


	
	/**
	 * 2. 进入下载列表
	 */
	private void downList(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		// 实现思路：先获取upload目录下所有文件的文件名，再保存；跳转到down.jsp列表展示
		
		//1. 初始化map集合Map<包含唯一标记的文件名, 简短文件名>  ;
		Map<String,String> fileNames = new HashMap<String,String>();
		Map<String,String> fileNames1 = new HashMap<String,String>();
		String shortName2="";
		//2. 获取上传目录，及其下所有的文件的文件名
		String bathPath = getServletContext().getRealPath("/upload");
		// 目录
		File file = new File(bathPath);
		// 目录下，所有文件名
		String list[] = file.list();
		// 遍历，封装
		if (list != null && list.length > 0){
			for (int i=0; i<list.length; i++){
				// 全名
				String fileName = list[i];
				//获取文件最后修改时间
				File f1=new File(bathPath+"/"+fileName);
				Calendar cal = Calendar.getInstance();
				long time1 = f1.lastModified();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				cal.setTimeInMillis(time1);
				// 短名1
				String shortName = fileName.substring(fileName.lastIndexOf("#")+1);
			//	String shortName = fileName.split("#")[1];
				//短名2
				// shortName2=fileName.split("@")[0];
				shortName2=formatter.format(cal.getTime());
				
				// 封装
				fileNames.put(fileName, shortName);
				fileNames1.put(fileName, shortName2);
			}
		}
		
		// 3. 保存到request域
		request.setAttribute("fileNames", fileNames);
		request.setAttribute("filename", fileNames1);
	//	request.setAttribute("uptime", shortName2);
		// 4. 转发
		request.getRequestDispatcher("/DownloadFile.jsp").forward(request, response);

	}

	
	/**
	 *  3. 处理下载
	 */
	private void down(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  
        response.setContentType("text/html;charset=utf-8");  
		// 获取用户下载的文件名称(url地址后追加数据,get)
		String fileName = request.getParameter("fileName");
	//	fileName = new String(fileName.getBytes("ISO8859-1"),"UTF-8");
		// 先获取上传目录路径
		String basePath = getServletContext().getRealPath("/upload");
		// 获取一个文件流
		InputStream in = new FileInputStream(new File(basePath,fileName));
		
		// 如果文件名是中文，需要进行url编码
		fileName=fileName.substring(fileName.indexOf('#')+1);
		fileName = URLEncoder.encode(fileName, "UTF-8");
		// 设置下载的响应头
		response.setHeader("content-disposition", "attachment;fileName=" + fileName);
		
		// 获取response字节流
		OutputStream out = response.getOutputStream();
		byte[] b = new byte[1024];
		int len = -1;
		while ((len = in.read(b)) != -1){
			out.write(b, 0, len);
		}
		// 关闭
		out.close();
		in.close();
		
		
	}
	
	/*
	 * 删除文件(单个)
	 */
	private void deletefile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");  
        response.setContentType("text/html;charset=utf-8");  
        PrintWriter out = response.getWriter();

        //得到要下载的文件名
           String fileName = request.getParameter("fileName");  
           //获取preview中相应的pdf和swf文件
           String newname=fileName.replace('#', '_');
           newname=newname.substring(0,newname.lastIndexOf('.')+1);
           newname=newname+"swf";
           String newname2=newname+"pdf";
      //     fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
           //上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
       	String basePath = getServletContext().getRealPath("/upload");
       	String changePath = getServletContext().getRealPath("/preview");
           //通过文件名找出文件的所在目录,存放路径
      //     String path = this.getServletContext().getRealPath("/WEB-INF/upload");
           //得到要下载的文件
           File file = new File(basePath , fileName);
           File file2 = new File(changePath , newname);
           File file3 = new File(changePath , newname2);
           if(file.isFile() && file.exists()){    
                file.delete(); 
                file2.delete();
                file3.delete();
             //   response.sendRedirect("admin/DataView.jsp");  
             //   System.out.println("删除单个文件"+fileName+"成功！"); 
                out.print("<script>alert('删除成功');location.href='/MIS/DownloadFile.jsp';</script>");	
                
            }else{    
             //    response.sendRedirect("admin/DataView.jsp");  
             //   System.out.println("删除单个文件"+fileName+"失败！");   
            	out.print("<script>alert('删除失败');location.href='/MIS/DownloadFile.jsp';</script>");	
            } 

        out.flush();
        out.close();
	}
	
	
	/*
	 * 删除文件（群体）
	 */
	private void pluralityfile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");  
        response.setContentType("text/html;charset=utf-8");  
        PrintWriter out = response.getWriter();

        //得到要下载的文件名
           String fileName = request.getParameter("parameter");
           //获取preview中相应的pdf和swf文件
          String []fi=fileName.split("[+]");
          String[] filegroup=fi[0].split(" ");
          String newname="";                    //提取同一文件名的不同格式，如与ppt/doc搭配的pdf,swf之类的
          String newname1="";
          String newname2="";
          for(int i=0;i<filegroup.length;i++){
           newname="";newname2="";newname1="";
           newname=filegroup[i].substring(0,filegroup[i].lastIndexOf('.')+1);
           newname1=newname+"swf";
           newname2=newname+"pdf";
           filegroup[i]=filegroup[i].replace('_','#');
       	String basePath = getServletContext().getRealPath("/upload");
       	String changePath = getServletContext().getRealPath("/preview");
           File file = new File(basePath , filegroup[i]);
           File file2 = new File(changePath , newname1);
           File file3 = new File(changePath , newname2);
           if(file.isFile() && file.exists()){    
                file.delete(); 
                file2.delete();
                file3.delete();
            //    out.print("<script>alert('删除成功');location.href='/F0Fproject/DownloadFile.jsp';</script>");	
                
            }
          }
          out.print("<script>alert('删除成功');location.href='/MIS/DownloadFile.jsp';</script>");	
        out.flush();
        out.close();             
	} 
	
	
	/*
	 * 根据关键字查找文件
	 */
	private void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		Map<String,String> fileNames = new HashMap<String,String>();
		Map<String,String> fileNames1 = new HashMap<String,String>();
		int flag=0;            //判断是否存在文件
		//获得输入值
		  String keyword=request.getParameter("keyword");
		//文件保存路径
		String bathPath = getServletContext().getRealPath("/upload");
		// 目录
		File file = new File(bathPath);
		// 目录下，所有文件名
		String list[] = file.list();
		// 遍历，封装
		if (list != null && list.length > 0){
			for (int i=0; i<list.length; i++){
				// 全名
				String fileName = list[i];
				//获取文件最后修改时间
				File f1=new File(bathPath+"/"+fileName);
				 String truename=fileName.substring(fileName.lastIndexOf('#')+1,fileName.lastIndexOf('.'));
				 String suffixname = fileName.substring(fileName.lastIndexOf(".")+1);   //根据不同的后缀名采用不同的文本化方式
				 //单纯的文件名
				 if(truename.indexOf(keyword)!=-1 || keyword.indexOf(truename)!=-1){
					 String shortName = fileName.substring(fileName.lastIndexOf("#")+1);
					  Calendar cal = Calendar.getInstance();
						long time1 = f1.lastModified();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						cal.setTimeInMillis(time1);
					String	shortName2=formatter.format(cal.getTime());
					  fileNames.put(fileName, shortName);
					  fileNames1.put(fileName, shortName2);
				 }
				 
			     //html,txt,xml
				 if(suffixname.equals("txt")||suffixname.equals("html")||suffixname.equals("xml")){
				 try {
					  String encoding="GBK";
					 // File file=new File(filePath);
					  if(f1.isFile() && f1.exists()){ //判断文件是否存在
					  InputStreamReader read = new InputStreamReader(
					  new FileInputStream(f1),encoding);//考虑到编码格式
					  BufferedReader bufferedReader = new BufferedReader(read);
					  String lineTxt = null;
					  while((lineTxt = bufferedReader.readLine()) != null){
					   int pos=lineTxt.indexOf(keyword);
					  if(pos!=-1){
						  String shortName = fileName.substring(fileName.lastIndexOf("#")+1);
						  Calendar cal = Calendar.getInstance();
							long time1 = f1.lastModified();
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							cal.setTimeInMillis(time1);
						String	shortName2=formatter.format(cal.getTime());
						  fileNames.put(fileName, shortName);
						  fileNames1.put(fileName, shortName2);
						  break;
					  }
					  }
					  read.close();
					  }
					  } catch (Exception e) {
					  System.out.println("读取文件内容出错");
					  e.printStackTrace();
					  }
			}
			//doc docx
		    else if(suffixname.equals("doc")||suffixname.equals("docx")){
		    	String result = null;  
		    	 InputStream is = null;  
			        XWPFDocument doc = null; 
			        try{  
			        	if(suffixname.equals("doc")){
			              FileInputStream fis = new FileInputStream(f1);  
			             //得到.doc文件提取器   
			              WordExtractor wordExtractor = new WordExtractor(fis);  
			             //提取.doc正文文本   
			             result = wordExtractor.getText();  
			             //提取.doc批注    
			             //String[] comments = wordExtractor.getCommentsText();   
			             wordExtractor.close(); 
			        	}
			        	else{
			        		is = new FileInputStream(f1);
				        	doc = new XWPFDocument(is);
				        	XWPFWordExtractor extractor = new XWPFWordExtractor(doc);    
				        	result=extractor.getText();
				        	extractor.close();
			        	}
			        }catch(FileNotFoundException e){  
			        e.printStackTrace();  
			    }catch(IOException e){  
			        e.printStackTrace();  
			        };  
			       if(result.indexOf(keyword)!=-1){
			    	   String shortName = fileName.substring(fileName.lastIndexOf("#")+1);
						  Calendar cal = Calendar.getInstance();
							long time1 = f1.lastModified();
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							cal.setTimeInMillis(time1);
						String	shortName2=formatter.format(cal.getTime());
						  fileNames.put(fileName, shortName);
						  fileNames1.put(fileName, shortName2);
			       }
		    }
		
			//xls xlsx
		    else if(suffixname.equals("xls")||suffixname.equals("xlsx")){
		    	InputStream is = null;  
		        HSSFWorkbook wb = null; 
		        XSSFWorkbook workBook = null;  
		        String text="";  
		        try {  
		            is = new FileInputStream(f1);  
		            if(suffixname.equals("xls")){
		            wb = new HSSFWorkbook(new POIFSFileSystem(is));  
		            ExcelExtractor extractor=new ExcelExtractor(wb);  
		            extractor.setFormulasNotResults(false);  
		            extractor.setIncludeSheetNames(true);  	            
		            text=extractor.getText();  
		            extractor.close();
		            }
		            else{
		            	 workBook = new XSSFWorkbook(is);  
		                 XSSFExcelExtractor extractor=new XSSFExcelExtractor(workBook);  
		                 text=extractor.getText();  
		                 extractor.close(); 
		            }
		        }  catch (FileNotFoundException e) {  
		            System.out.println("没有找到指定路径"+f1);  
		            e.printStackTrace();  
		        } catch (IOException e) {  
		            System.out.println("getTextFromExcel2007 IO错误");  
		            e.printStackTrace();  
		        }  
		        if(text.indexOf(keyword)!=-1){
		        	 String shortName = fileName.substring(fileName.lastIndexOf("#")+1);
					  Calendar cal = Calendar.getInstance();
						long time1 = f1.lastModified();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						cal.setTimeInMillis(time1);
					String	shortName2=formatter.format(cal.getTime());
					  fileNames.put(fileName, shortName);
					  fileNames1.put(fileName, shortName2);
		        }
		    }
			//ppt pptx
		    else if(suffixname.equals("ppt")||suffixname.equals("pptx")){
		    	  InputStream is = null;  
		          PowerPointExtractor extractor = null;  
		          XMLSlideShow slide = null;  
		          String text="";  
		          try {  
		              is = new FileInputStream(f1);  
		              if(suffixname.equals("ppt")){
		              extractor = new PowerPointExtractor(is);  
		              text=extractor.getText();  
		              extractor.close();  
		              }
		              else{
		            	  slide = new XMLSlideShow(is);  
		                  XSLFPowerPointExtractor extractor1=new XSLFPowerPointExtractor(slide);  
		                  text=extractor1.getText();  
		                  extractor1.close(); 
		              }
		          } catch(Exception e){
		        	  e.printStackTrace();
		          }
		          if(text.indexOf(keyword)!=-1){
			        	 String shortName = fileName.substring(fileName.lastIndexOf("#")+1);
						  Calendar cal = Calendar.getInstance();
							long time1 = f1.lastModified();
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							cal.setTimeInMillis(time1);
						String	shortName2=formatter.format(cal.getTime());
						  fileNames.put(fileName, shortName);
						  fileNames1.put(fileName, shortName2);
			        }
		    }
			
			//pdf
		    else if(suffixname.equals("pdf")){
		    	  File pdfFile = f1;
		          PDDocument document = null;
		          try{
		        	  document=PDDocument.load(pdfFile);

		              // 获取页码
		              int pages = document.getNumberOfPages();

		              // 读文本内容
		              PDFTextStripper stripper=new PDFTextStripper();
		              // 设置按顺序输出
		              stripper.setSortByPosition(true);
		              stripper.setStartPage(1);
		              stripper.setEndPage(pages);
		              String text = stripper.getText(document);
		              if(text.indexOf(keyword)!=-1){
				        	 String shortName = fileName.substring(fileName.lastIndexOf("#")+1);
							  Calendar cal = Calendar.getInstance();
								long time1 = f1.lastModified();
								SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								cal.setTimeInMillis(time1);
							String	shortName2=formatter.format(cal.getTime());
							  fileNames.put(fileName, shortName);
							  fileNames1.put(fileName, shortName2);
				        }
		          }catch(Exception e){
		        	  e.printStackTrace();
		          }
		    }
			
			}
		}
		request.setAttribute("fileNames", fileNames);
		request.setAttribute("filename", fileNames1);
	//	request.setAttribute("uptime", shortName2);
		// 4. 转发
		request.getRequestDispatcher("/DownloadFile.jsp").forward(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

}

