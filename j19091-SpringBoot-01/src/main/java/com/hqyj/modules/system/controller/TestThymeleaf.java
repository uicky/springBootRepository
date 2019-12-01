package com.hqyj.modules.system.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(value="/testthymeleaf")
public class TestThymeleaf {
	private static final Logger LOGGER=LoggerFactory.getLogger(TestController.class);
	
	/**
	* <p>Title: </p>  
	* <p>Description:跳转到主页面 </p>  
	* @param modelMap
	* @return
	 */
	@RequestMapping(value = "/index")
	public String testThymeleaf(ModelMap modelMap) {
		/* modelMap.addAttribute("template", "test/index"); */
		return "index";
	}
	
	
	/**
	 * 
	 * 上传单个文件，虽然是form表单，但file是以参数的形式传递的，采用requestParam注解接收MultipartFile
	 */
	@RequestMapping(value="/upload", method=RequestMethod.POST, consumes="multipart/form-data")
	public String uploadFile(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
		
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select file.");
			return "redirect:/testthymeleaf/index";
		}
		
		try {
			String fileName = file.getOriginalFilename();
			String destFileName = "D:/upload" + File.separator + fileName;
			
			File destFile = new File(destFileName);
			file.transferTo(destFile);
			
			// 使用工具类Files来上传文件
//			byte[] bytes = file.getBytes();
//			Path path = Paths.get(destFileName);
//			Files.write(path, bytes);
			
			redirectAttributes.addFlashAttribute("message", "upload file success.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "upload file fail.");
			e.printStackTrace();
			LOGGER.debug(e.getMessage());
		}
		
		return "redirect:/testthymeleaf/index";
	}
	
	/**
	* <p>Title: </p>  
	* <p>Description: 通过字符流上传文件,通过字符流的方式没有最大限制</p>  
	* @param file
	* @param redirectAttributes
	* @return
	 */
	@RequestMapping(value="/uploadFile",method=RequestMethod.POST,consumes="multipart/form-data")
	public String uploadOneFile(@RequestParam MultipartFile file,RedirectAttributes redirectAttributes) {
		
		//如果文件为空
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message","place select one file.");
			return "redirect:/testthymeleaf/index";
		}
		 try {
			 //得到原始文件名
			String fileName = file.getOriginalFilename();
			fileName = URLDecoder.decode(fileName, "UTF-8");
			//保存路径+文件名
			String destFileName = "D:/upload" + File.separator + fileName;
			byte[] bytes = file.getBytes();
			Path path=Paths.get(destFileName);
			Files.write(path, bytes);
			redirectAttributes.addFlashAttribute("message","upload file success."); 
			
			
			/* 第二种通过字符流的方式上传文件，uploadFile()方法为自定义的方法
			 * boolean result = UploadFile.uploadFile(bytes, path, fileName);
			 * if (result) {
			 * 			redirectAttributes.addFlashAttribute("message","upload file success."); 
			 * }else{ 
			 * 		redirectAttributes.addFlashAttribute("message","upload file fail.");
			 *  }
			 */
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			LOGGER.error("---不支持的编码异常----",e);
			redirectAttributes.addFlashAttribute("message","upload file fail.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("----IO异常---",e);
			redirectAttributes.addFlashAttribute("message","upload file fail.");
		}
		 return "redirect:/testthymeleaf/index";
	}
	
	/**
	* <p>Title: </p>  
	* <p>Description:上传多个文件, </p> 
	* 上传耽搁文件，虽然是form表单，但file是以参数的形式传递的，采用requestParam注解
	* 接收MultipartFile
	* @param files
	* @param redirectAttributes
	* @return
	*/
	@RequestMapping(value="/uploadFiles",method=RequestMethod.POST,consumes="multipart/form-data")
	public String uploadBatchFile(@RequestParam MultipartFile[] files,RedirectAttributes redirectAttributes) {
		//设置标志为判断文件是否为空，默认都为空，只要有一个上传成功，就不用提示，“请选择文件”的信息。
		boolean isEmpty=true;
		try {
			for (MultipartFile file : files) {
				if (file.isEmpty()) {
					continue;
				}
				//得到文件名
				String fileName=file.getOriginalFilename();
				//指定存储路径
				String destFileName="D:/upload"+File.separator+fileName;
				
				// 使用MultipartFile的transferTo方法保存文件
				File destFile = new File(destFileName);
				file.transferTo(destFile);
				isEmpty=false;
			}
			
		} catch (Exception e) {
			//使用日志打印异常信息
			LOGGER.debug(e.getMessage());
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "upload file fail.");
			return "redirect:/testthymeleaf/index";
		}
		if (isEmpty) {
			redirectAttributes.addFlashAttribute("message", "pleace select a file.");
			
		} else {
			redirectAttributes.addFlashAttribute("message", "upload file success.");
		}
		return "redirect:/testthymeleaf/index";
	}
	
	/**
	 * 
	* <p>Title: </p>  
	* <p>Description:下载文件 </p>  
	* 'Content-Type': 'application/octet-stream',
	* @param fileName
	* @return ResponseEntity ---- spring专门包装响应信息的类
	 */
	@RequestMapping(value = "/download")
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestParam String fileName) {
		try {
			// 使用resource来包装下载文件
			Resource resource = new UrlResource(
					Paths.get("D:/upload" + File.separator + fileName).toUri());
			if (resource.exists() && resource.isReadable()) {
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + 
								resource.getFilename() + "\"")
						.body(resource);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug(e.getMessage());
		}
		
		return null;

	}
	
	
//	+++++++++++++++++++++++++++++++
	/**
	 * 
	* <p>Title: </p>  
	* <p>Description:下载文件的第二种方式 </p>  
	* @param fileName
	* @return
	 */
	@RequestMapping("/downloadCacheFile")
	@ResponseBody
	public ResponseEntity<Resource> downloadCacheFile(@RequestParam("fileName") String fileName) {
	    try {
	        String savePath = "D:/upload/";
	        // 获取文件名称，中文可能被URL编码
	        fileName = URLDecoder.decode(fileName, "UTF-8");
	        // 获取本地文件系统中的文件资源
	        FileSystemResource resource = new FileSystemResource(savePath + fileName);

	        // 解析文件的 mime 类型
	        String mediaTypeStr = URLConnection.getFileNameMap().getContentTypeFor(fileName);
	        // 无法判断MIME类型时，作为流类型
	        mediaTypeStr = (mediaTypeStr == null) ? MediaType.APPLICATION_OCTET_STREAM_VALUE : mediaTypeStr;
	        // 实例化MIME
	        MediaType mediaType = MediaType.parseMediaType(mediaTypeStr);


	        /*
	         * 构造响应的头
	         */
	        HttpHeaders headers = new HttpHeaders();
	        // 下载之后需要在请求头中放置文件名，该文件名按照ISO_8859_1编码。
	        String filenames = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
	        headers.setContentDispositionFormData("attachment", filenames);
	        headers.setContentType(mediaType);

	        /*
	         * 返还资源
	         */
	        return ResponseEntity.ok()
	                .headers(headers)
	                .contentLength(resource.getInputStream().available())
	                .body(resource);
	    } catch (IOException e) {
	        LOGGER.error("文件读写错误", e);
	        return null;
	   }
	}
	
	/**
	* <p>Title: </p>  
	* <p>Description:通过IO流实现文件下载 </p>  
	* @param fileName
	* @param request
	* @param response
	 */
	@RequestMapping(value="/dowloadFile")
	public void downloadFileByStream(@RequestParam String fileName,HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) {
		//TODO 本方法还存在问题，图片还不能正常显示
		response.setCharacterEncoding("UTF-8");
		try {
			fileName=new String(fileName.getBytes("iso8859-1"),"utf-8");
			String path="D:/upload" + File.separator + fileName;
			File file=new File(path);
			
			if (!file.exists()) {
				modelMap.addAttribute("message", "要下载的资源不存在");
				LOGGER.debug("要下载的资源不存在");
				return;
			}
			
			  //控制浏览器下载该文件
	        response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode(fileName,"UTF-8"));
			
	        //  读取需要下载的文件 保存到文件输入流
	        FileInputStream fileInputStream = new FileInputStream(path);
	        //创建输出流
	        OutputStream fileOutputStream = response.getOutputStream();
		
	        //创建缓冲区
	       byte[] buffer= new byte[1024];
	       int len=0;
	       
	       while((len=fileInputStream.read(buffer))!=-1) {
	    	   fileOutputStream.write(buffer, 0, len);
	       }
	       //关闭流
	       fileInputStream.close();
	       fileOutputStream.close();
		
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("----要下载的资源不存在---",e);
		} catch (FileNotFoundException e) {
			LOGGER.error("----文件找不到异常---",e);
		} catch (IOException e) {
			LOGGER.error("----IO异常---",e);
		}
		
		
		
		
	}
	


}
