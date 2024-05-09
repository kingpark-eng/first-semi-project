package com.projects.firstsemiproject.Service.Impl;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Service;

import com.projects.firstsemiproject.Service.FileService;
import com.projects.firstsemiproject.VO.FileVO;

@Service(value = "fileService")
public class FileServiceImpl implements FileService{

	private static final String TXT_EXT = ".txt";
	private static final String TXT_ZIP = ".zip";
	private static final String SEPARATOR = File.separator;
	
	@Override
	public void makeFile(FileVO inVO) throws Exception{
		String fileName = "";
		String filePath = "";
		
		fileName = inVO.getFileName();
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
		
		FilenameFilter filenameFilter = (file, name) -> name.endsWith(".txt");
		
		for(File fileList : fileDir.listFiles(filenameFilter)) {
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
