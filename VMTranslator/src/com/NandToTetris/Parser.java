package com.NandToTetris;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

	private Scanner sc;
	private String currentCommand;
	private Command currentCommandType;
	private String arithmeticLogicalCommands[] = {"add", "sub", "neg", "eq", "gt", "lt", "and", "or", "not"};

	public Parser(File file) throws FileNotFoundException {
		sc = new Scanner(file);
	}

	public void print() {
		while (sc.hasNextLine()) {
			System.out.println(sc.nextLine());
		}
	}

	public boolean hasMoreCommands() {
		// finding valid command
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			line = line.trim();

			// checking for comments or empty lines
			if (line.indexOf("//") == 0 || line.equals("")) {
				continue;
			} else {
				// valid command
				currentCommand = line;
				return true;
			}
		}

		return false;
	}

	public void advance() {

	}

	public Command commandType() {
		if (currentCommand != null) {
			
			String commandTokens[] = currentCommand.split(" ");
			
			// check for arithmetic or logical command
			for(String cmd: arithmeticLogicalCommands){
				if(commandTokens[0].equals(cmd)){
					return Command.C_ARITHMETIC;
				}
			}
						
			switch(commandTokens[0].toUpperCase()){
			case("PUSH"):
				return Command.C_PUSH;
			case("POP"):
				return Command.C_POP;
			case("GOTO"):
				return Command.C_GOTO;
			case("IF-GOTO"):
				return Command.C_IF_GOTO;
			case("LABEL"):
				return Command.C_LABEL;
			case("CALL"):
				return Command.C_CALL;
			case("FUNCTION"):
				return Command.C_FUNCTION;
			case("RETURN"):
				return Command.C_RETURN;
			}
			
		}
		return null;
	}

	public String arg1() {
		// return only on arithmetic command type not on return type
		String commandTokens[] = currentCommand.split(" ");
		Command commandType = this.commandType();
		if(commandType == Command.C_ARITHMETIC){
			return commandTokens[0];
		}else if(commandType == Command.C_PUSH || commandType == Command.C_POP){
			return commandTokens[1];
		}else if(commandType != Command.C_RETURN){
			return commandTokens[1];
		}
		
		return null;
	}

	public int arg2() {
		// return on push, pop, function or call.
		String commandTokens[] = currentCommand.split(" ");
		Command commandType = this.commandType();
		
		switch(commandType){
		case C_PUSH:
		case C_POP:
		case C_FUNCTION:
		case C_CALL:
			return Integer.parseInt(commandTokens[2].split("\t")[0].trim());
		default:
			break;
		}
		return -1;
	}
}
