package com.projects.firstsemiproject.Controller;

import java.io.File;import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projects.firstsemiproject.VO.FileVO;

@RestController
public class FileController {

	private static final String TXT_EXT = ".txt";
	private static final String TXT_ZIP = ".zip";
	private static final String SEPARATOR = File.separator;
	
	@PostMapping
	public void makeFile(@RequestBody FileVO inVO) throws Exception{
		
		String fileName = "";
		String filePath = "";
		
		fileName = inVO.getFileNm();
		filePath = inVO.getFilePath();
		
		//파일 생성 및 디렉토리 확인
		File fileDir = new File(filePath);
		File file = new File(filePath+SEPARATOR+fileName+TXT_EXT);
		
		if(!fileDir.exists()) {
			fileDir.mkdir();
		}

		FileOutputStream fos = new FileOutputStream(file);
		fos.close();
		
		makeZip(fileDir, filePath, fileName);
		
	}
	
	private int makeZip(File fileDir, String filePath, String fileName) throws Exception{
		
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File(filePath+SEPARATOR+fileName+TXT_ZIP)));
		
		for(File fileList : fileDir.listFiles()) {
			//파일 먼저 넣기
			ZipEntry entry = new ZipEntry(fileList.getName());
			zos.putNextEntry(entry);
			FileInputStream fis = new FileInputStream(fileList);
			byte[] b = new byte[1026];
			int length = 0;
			while((length = fis.read(b)) > -1) {
				zos.write(b, 0, length);
			}
			fis.close();
		}
		
		zos.close();
		
		return 0;
	}
}
