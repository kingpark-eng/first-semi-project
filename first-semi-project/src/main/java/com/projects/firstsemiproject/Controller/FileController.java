package com.projects.firstsemiproject.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projects.firstsemiproject.Service.FileService;
import com.projects.firstsemiproject.VO.FileVO;

import jakarta.annotation.Resource;

@RestController
public class FileController {

	@Resource(name = "fileService")
	private FileService fileService;

	/**
	 * 
	 * @author kingp
	 * @param 파일로 만들어질 파일정보
	 * @return void
	 * 
	 * */
	@PostMapping(value = "makeFile")
	public void makeFile(@RequestBody FileVO inVO) throws Exception{
		fileService.makeFile(inVO);
	}
	
}
