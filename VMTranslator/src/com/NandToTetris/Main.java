package com.NandToTetris;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;

public class Main {
	
	public static String currentLabel = null;
	public static String currentFileName = null;
	public static String currentFunctionName = null;
	
	public static void main(String args[]) throws IOException {
		//File file = new File("./StaticsTest");
		 File file = new File(args[0]);

		// getting file name without extension
		String fileName = file.getName();
		int indexOfDot = fileName.indexOf(".");
		if (indexOfDot > -1) {
			fileName = fileName.substring(0, indexOfDot);
		}
		Parser parser = null;
		CodeWriter codeWriter = null; // writing in assembly file
		
		if (file.isFile()) {
			parser = new Parser(file); // reading input VM code
			codeWriter = new CodeWriter(fileName);
			codeWriter.setFileName(fileName);
			codeWriter.writeInit();
			process(parser, codeWriter);
		} else if (file.isDirectory()) {
			codeWriter = new CodeWriter(fileName + "/" + fileName);
			codeWriter.writeInit();
			handleDir(file, parser, codeWriter, fileName);
		}

		if(codeWriter != null){
			codeWriter.close();
		}
	}

	public static void process(Parser parser, CodeWriter codeWriter) throws IOException {
		while (parser.hasMoreCommands()) {
			parser.advance();
			Command commandType = parser.commandType();
			
			switch(commandType){
			case C_PUSH:
			case C_POP:
				codeWriter.writePushPop(commandType, parser.arg1(), parser.arg2());
				break;
			case C_ARITHMETIC:
				codeWriter.writeArithmetic(parser.arg1());
				break;
			case C_CALL:
				codeWriter.writeCall(parser.arg1(), parser.arg2());
				break;
			case C_FUNCTION:
				codeWriter.writeFunction(parser.arg1(), parser.arg2());
				currentFunctionName = parser.arg1();
				break;
			case C_GOTO:
				currentLabel = currentFunctionName + "$" + parser.arg1();
				codeWriter.writeGoto(currentLabel);
				break;
			case C_IF_GOTO:
				currentLabel = currentFunctionName + "$" + parser.arg1();
				codeWriter.writeIf(currentLabel);
				break;
			case C_LABEL:
				currentLabel = currentFunctionName + "$" + parser.arg1();
				codeWriter.writeLabel(currentLabel);
				break;
			case C_RETURN:
				codeWriter.writeReturn();
				break;
			default:
				break;
			}
		}
	}

	public static void handleDir(File file, Parser parser, CodeWriter codeWriter, String fileName) throws IOException {
		// checking if file
		if (file.isFile()) {
			parser = new Parser(file); // reading input VM code
			codeWriter.setFileName(fileName);
			process(parser, codeWriter);
		} else if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				if (f.isFile()) {
					if(f.getName().endsWith(".vm")){
						parser = new Parser(f); // reading input VM code
						String fName = f.getName();
						int indexOfDot = fName.indexOf(".");
						if (indexOfDot > -1) {
							fName = fName.substring(0, indexOfDot);
						}
						codeWriter.setFileName(fName);
						process(parser, codeWriter);
					}
				}else if(f.isDirectory()){
					handleDir(f, parser, codeWriter, fileName);
				}
			}
		}
		
	}
}
