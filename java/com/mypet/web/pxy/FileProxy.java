package com.mypet.web.pxy;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.mypet.web.enums.Path;
@Component("filemgr")
public class FileProxy extends Proxy{
	public void fileUpload(MultipartFile[] uploadFile) {
		String uploadFolder = Path.UPLOAD_PATH.toString();
//		File uploadPath = new File(uploadFolder, getFolder());
		File uploadPath = makeDir(uploadFolder, getFolder());
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		for(MultipartFile f:uploadFile) {
			String fname = f.getOriginalFilename();
			String extension = fname.substring(fname.lastIndexOf(".")+1);
			fname = fname.substring(fname.lastIndexOf("\\")+1, fname.lastIndexOf("."));
			File saveFile = makeFile(uploadPath, fname + "_" + UUID.randomUUID().toString() + "." + extension);
//			File saveFile = new File(uploadPath, fname + "_" + UUID.randomUUID().toString() + "." + extension);
			try {
				f.transferTo(saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}
	public String getFolder() {
		return currentDate().replace("-", File.separator);
	}
}
