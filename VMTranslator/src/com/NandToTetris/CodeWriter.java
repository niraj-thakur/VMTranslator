package com.NandToTetris;

import java.io.FileWriter;
import java.io.IOException;

public class CodeWriter {

	private FileWriter fileWriter;
	private static int logicalCounter = 0;
	private static int callCounter = 0;
	private String fileName;

	public CodeWriter(String fileName) throws IOException {
		fileWriter = new FileWriter(fileName + ".asm");
	}

	public void setFileName(String fileName) throws IOException {
		this.fileName = fileName;
	}

	public void writeInit() throws IOException {
		StringBuffer sb = new StringBuffer();
		
		// SP = 256
		sb.append("// SP = 256 ").append(System.lineSeparator());
		sb.append("@256").append(System.lineSeparator());
		sb.append("D = A").append(System.lineSeparator());
		
		sb.append("@SP").append(System.lineSeparator());
		sb.append("M = D").append(System.lineSeparator());
		
		// call Sys.init
		sb.append("// call Sys.init").append(System.lineSeparator());
		sb.append(callAssembly("Sys.init", 0)).append(System.lineSeparator());
		
		fileWriter.write(sb.toString());
		
	}

	public void writeArithmetic(String command) throws IOException {
		StringBuffer sb = new StringBuffer();
		switch (command.toUpperCase()) {
		case ("ADD"):
			sb.append("// add").append(System.lineSeparator());
			sb.append(twoOperandAssembly("+"));
			break;
		case ("SUB"):
			sb.append("// sub").append(System.lineSeparator());
			sb.append(twoOperandAssembly("-"));
			break;
		case ("NEG"):
			sb.append("// neg").append(System.lineSeparator());
			sb.append(oneOperandAssembly("-"));
			break;
		case ("EQ"):
			sb.append("// eq").append(System.lineSeparator());
			sb.append(booleanAssembly("eq"));
			break;
		case ("GT"):
			sb.append("// gt").append(System.lineSeparator());
			sb.append(booleanAssembly("gt"));
			break;
		case ("LT"):
			sb.append("// lt").append(System.lineSeparator());
			sb.append(booleanAssembly("lt"));
			break;
		case ("AND"):
			sb.append("// and").append(System.lineSeparator());
			sb.append(twoOperandAssembly("&"));
			break;
		case ("OR"):
			sb.append("// or").append(System.lineSeparator());
			sb.append(twoOperandAssembly("|"));
			break;
		case ("NOT"):
			sb.append("// not").append(System.lineSeparator());
			sb.append(oneOperandAssembly("!"));
			break;
		}
		fileWriter.write(sb.toString());
	}

	public void writePushPop(Command command, String segment, int index) throws IOException {
		switch (segment.toUpperCase()) {
		case ("CONSTANT"):
			fileWriter.write(constantSegmentAssembly(command, index));
			break;
		case ("LOCAL"):
		case ("ARGUMENT"):
		case ("THIS"):
		case ("THAT"):
			fileWriter.write(memory4SegmentAssembly(command, segment, index));
			break;
		case ("STATIC"):
			fileWriter.write(staticSegmentAssembly(command, index));
			break;
		case ("TEMP"):
			fileWriter.write(tempSegmentAssembly(command, index));
			break;
		case ("POINTER"):
			fileWriter.write(pointerSegmentAssembly(command, index));
			break;
		}
	}

	public void writeLabel(String label) throws IOException {
		StringBuffer sb = new StringBuffer();

		sb.append("// label ").append(label).append(System.lineSeparator());
		sb.append("(").append(label).append(")").append(System.lineSeparator());

		fileWriter.write(sb.toString());
	}

	public void writeGoto(String label) throws IOException {
		StringBuffer sb = new StringBuffer();

		sb.append("// goto ").append(label).append(System.lineSeparator());
		sb.append("@").append(label).append(System.lineSeparator());
		sb.append("0;JMP").append(System.lineSeparator());

		fileWriter.write(sb.toString());
	}

	public void writeIf(String label) throws IOException {
		StringBuffer sb = new StringBuffer();

		sb.append("// if-goto ").append(label).append(System.lineSeparator());

		sb.append("@SP").append(System.lineSeparator());
		sb.append("M = M - 1").append(System.lineSeparator());

		sb.append("@SP").append(System.lineSeparator());
		sb.append("A = M").append(System.lineSeparator());
		// if -1(any value other than 0) = true, 0 = false
		sb.append("D = M").append(System.lineSeparator());

		sb.append("@").append(label).append(System.lineSeparator());
		sb.append("D ; JNE").append(System.lineSeparator());

		fileWriter.write(sb.toString());
	}

	public void writeFunction(String functionName, int numVars) throws IOException {
		StringBuffer sb = new StringBuffer();

		sb.append("// function ").append(functionName).append(" ").append(numVars).append(System.lineSeparator());

		sb.append("(").append(functionName).append(")").append(System.lineSeparator());

		// loop and set 0 to local variables
		for (int i = 0; i < numVars; i++) {
			// push constant 0
			sb.append(constantSegmentAssembly(Command.C_PUSH, 0));
			// pop local i
			sb.append(memory4SegmentAssembly(Command.C_POP, "local", i));
			// SP ++
			sb.append("@SP").append(System.lineSeparator());
			sb.append("M = M + 1").append(System.lineSeparator());
			
		}

		fileWriter.write(sb.toString());
	}

	public void writeCall(String functionName, int numArgs) throws IOException {
		StringBuffer sb = new StringBuffer();

		sb.append("// call ").append(functionName).append(" ").append(numArgs).append(System.lineSeparator());
		sb.append(callAssembly(functionName, numArgs)).append(System.lineSeparator());

		fileWriter.write(sb.toString());
	}
	
	private String callAssembly(String functionName, int numArgs){
		StringBuffer sb = new StringBuffer();

		//sb.append("// call ").append(functionName).append(" ").append(numArgs).append(System.lineSeparator());

		// push returnAddress
		sb.append("@").append(functionName).append("$ret.").append(callCounter).append(System.lineSeparator());
		sb.append("D = A").append(System.lineSeparator());

		sb.append("@SP").append(System.lineSeparator());
		sb.append("A = M").append(System.lineSeparator());
		sb.append("M = D").append(System.lineSeparator());

		sb.append("@SP").append(System.lineSeparator());
		sb.append("M = M + 1").append(System.lineSeparator());

		// push LCL - save lcl of the caller
		sb.append("@LCL").append(System.lineSeparator());
		//sb.append("A = M").append(System.lineSeparator());
		sb.append("D = M").append(System.lineSeparator());

		sb.append("@SP").append(System.lineSeparator());
		sb.append("A = M").append(System.lineSeparator());
		sb.append("M = D").append(System.lineSeparator());

		sb.append("@SP").append(System.lineSeparator());
		sb.append("M = M + 1").append(System.lineSeparator());

		// push ARG - save ARG of the caller
		sb.append("@ARG").append(System.lineSeparator());
		//sb.append("A = M").append(System.lineSeparator());
		sb.append("D = M").append(System.lineSeparator());

		sb.append("@SP").append(System.lineSeparator());
		sb.append("A = M").append(System.lineSeparator());
		sb.append("M = D").append(System.lineSeparator());

		sb.append("@SP").append(System.lineSeparator());
		sb.append("M = M + 1").append(System.lineSeparator());
		
		// push THIS - save THIS of the caller
		sb.append("@THIS").append(System.lineSeparator());
		//sb.append("A = M").append(System.lineSeparator());
		sb.append("D = M").append(System.lineSeparator());

		sb.append("@SP").append(System.lineSeparator());
		sb.append("A = M").append(System.lineSeparator());
		sb.append("M = D").append(System.lineSeparator());

		sb.append("@SP").append(System.lineSeparator());
		sb.append("M = M + 1").append(System.lineSeparator());
		
		// push THAT - save THAT of the caller
		sb.append("@THAT").append(System.lineSeparator());
		//sb.append("A = M").append(System.lineSeparator());
		sb.append("D = M").append(System.lineSeparator());

		sb.append("@SP").append(System.lineSeparator());
		sb.append("A = M").append(System.lineSeparator());
		sb.append("M = D").append(System.lineSeparator());

		sb.append("@SP").append(System.lineSeparator());
		sb.append("M = M + 1").append(System.lineSeparator());
		
		// ARG = SP - 5 - nArgs
		sb.append("@").append(5 + numArgs).append(System.lineSeparator());
		sb.append("D = A").append(System.lineSeparator());
		
		sb.append("@SP").append(System.lineSeparator());
		sb.append("D = M - D").append(System.lineSeparator());
		
		sb.append("@ARG").append(System.lineSeparator());
		sb.append("M = D").append(System.lineSeparator());
		
		// LCL = SP
		sb.append("@SP").append(System.lineSeparator());
		sb.append("D = M").append(System.lineSeparator());
		
		sb.append("@LCL").append(System.lineSeparator());
		sb.append("M = D").append(System.lineSeparator());
		
		// goto functionName
		sb.append("@").append(functionName).append(System.lineSeparator());
		sb.append("0 ; JMP").append(System.lineSeparator());
		
		// (returnAddress)
		sb.append("(").append(functionName).append("$ret.")
		.append(callCounter).append(")").append(System.lineSeparator());

		callCounter++;
		
		return sb.toString();
	}

	public void writeReturn() throws IOException {
		StringBuffer sb = new StringBuffer();

		sb.append("// return").append(System.lineSeparator());

		// endFrame = LCL - endFrame is temp variable
		sb.append("@LCL").append(System.lineSeparator());
		sb.append("D = M").append(System.lineSeparator());

		sb.append("@endFrame").append(System.lineSeparator());
		sb.append("M = D").append(System.lineSeparator());

		// retAddr = *(endFrame - 5) - gets the return address
		sb.append("@5").append(System.lineSeparator());
		sb.append("D = A").append(System.lineSeparator());
		
		sb.append("@endFrame").append(System.lineSeparator());
		sb.append("A = M - D").append(System.lineSeparator());
		sb.append("D = M").append(System.lineSeparator());

		sb.append("@retAddr").append(System.lineSeparator());
		sb.append("M = D").append(System.lineSeparator());

		// *ARG = POP() - reposition the return value for the caller
		sb.append("@SP").append(System.lineSeparator());
		sb.append("M = M - 1").append(System.lineSeparator());
		sb.append("A = M").append(System.lineSeparator());
		sb.append("D = M").append(System.lineSeparator());

		sb.append("@ARG").append(System.lineSeparator());
		sb.append("A = M").append(System.lineSeparator());
		sb.append("M = D").append(System.lineSeparator());

		// SP = ARG + 1 - repositioning stack pointer
		sb.append("@ARG").append(System.lineSeparator());
		sb.append("D = M + 1").append(System.lineSeparator());

		sb.append("@SP").append(System.lineSeparator());
		sb.append("M = D").append(System.lineSeparator());

		// restoring segments

		// THAT = *(endFrame - 1)
		sb.append("@endFrame").append(System.lineSeparator());
		sb.append("A = M - 1").append(System.lineSeparator());
		sb.append("D = M").append(System.lineSeparator());

		sb.append("@THAT").append(System.lineSeparator());
		sb.append("M = D").append(System.lineSeparator());

		// THIS = *(endFrame - 2)
		sb.append("@2").append(System.lineSeparator());
		sb.append("D = A").append(System.lineSeparator());
		
		sb.append("@endFrame").append(System.lineSeparator());
		sb.append("A = M - D").append(System.lineSeparator());
		sb.append("D = M").append(System.lineSeparator());

		sb.append("@THIS").append(System.lineSeparator());
		sb.append("M = D").append(System.lineSeparator());

		// ARG = *(endFrame - 3)
		sb.append("@3").append(System.lineSeparator());
		sb.append("D = A").append(System.lineSeparator());
		
		sb.append("@endFrame").append(System.lineSeparator());
		sb.append("A = M - D").append(System.lineSeparator());
		sb.append("D = M").append(System.lineSeparator());

		sb.append("@ARG").append(System.lineSeparator());
		sb.append("M = D").append(System.lineSeparator());

		// LCL = *(endFrame - 4)
		sb.append("@4").append(System.lineSeparator());
		sb.append("D = A").append(System.lineSeparator());
		
		sb.append("@endFrame").append(System.lineSeparator());
		sb.append("A = M - D").append(System.lineSeparator());
		sb.append("D = M").append(System.lineSeparator());

		sb.append("@LCL").append(System.lineSeparator());
		sb.append("M = D").append(System.lineSeparator());

		// goto retAddr
		sb.append("@retAddr").append(System.lineSeparator());
		sb.append("A = M").append(System.lineSeparator());
		sb.append("0 ; JMP").append(System.lineSeparator());

		fileWriter.write(sb.toString());
	}

	public void close() throws IOException {
		if (fileWriter != null) {
			fileWriter.close();
		}
	}

	private String constantSegmentAssembly(Command command, int index) {
		StringBuffer sb = new StringBuffer();
		if (command == Command.C_PUSH) {

			sb.append("// push constant ").append(index).append(System.lineSeparator());

			sb.append("@").append(index).append(System.lineSeparator());
			sb.append("D = A").append(System.lineSeparator());

			sb.append("@SP").append(System.lineSeparator());
			sb.append("A = M").append(System.lineSeparator());
			sb.append("M = D").append(System.lineSeparator());

			sb.append("@SP").append(System.lineSeparator());
			sb.append("M = M + 1").append(System.lineSeparator());

		}
		return sb.toString();
	}

	// for local, argument, this, that segment
	private String memory4SegmentAssembly(Command command, String segment, int index) {
		StringBuffer sb = new StringBuffer();
		String segmentAssembly = "";

		switch (segment.toUpperCase()) {
		case ("LOCAL"):
			segmentAssembly = "LCL";
			break;
		case ("ARGUMENT"):
			segmentAssembly = "ARG";
			break;
		case ("THIS"):
			segmentAssembly = "THIS";
			break;
		case ("THAT"):
			segmentAssembly = "THAT";
			break;
		}

		if (command == Command.C_PUSH) {

			sb.append("// push ").append(segment).append(" ").append(index).append(System.lineSeparator());

			sb.append("@").append(segmentAssembly).append(System.lineSeparator());
			sb.append("D = M").append(System.lineSeparator());

			sb.append("@").append(index).append(System.lineSeparator()); // D =
																			// lcl
																			// +
																			// 2
			sb.append("D = D + A").append(System.lineSeparator());

			sb.append("@addr").append(System.lineSeparator()); // addr = D
			sb.append("M = D").append(System.lineSeparator());

			sb.append("@addr").append(System.lineSeparator());
			sb.append("A = M").append(System.lineSeparator());
			sb.append("D = M").append(System.lineSeparator());

			sb.append("@SP").append(System.lineSeparator());
			sb.append("A = M").append(System.lineSeparator());
			sb.append("M = D").append(System.lineSeparator());

			sb.append("@SP").append(System.lineSeparator());
			sb.append("M = M + 1").append(System.lineSeparator());

		} else if (command == Command.C_POP) {

			sb.append("// pop ").append(segment).append(" ").append(index).append(System.lineSeparator());

			sb.append("@").append(segmentAssembly).append(System.lineSeparator());
			sb.append("D = M").append(System.lineSeparator());

			sb.append("@").append(index).append(System.lineSeparator());
			sb.append("D = D + A").append(System.lineSeparator());

			sb.append("@addr").append(System.lineSeparator()); // addr = D
			sb.append("M = D").append(System.lineSeparator());

			sb.append("@SP").append(System.lineSeparator());
			sb.append("M = M - 1").append(System.lineSeparator());

			sb.append("@SP").append(System.lineSeparator());
			sb.append("A = M").append(System.lineSeparator());
			sb.append("D = M").append(System.lineSeparator());

			sb.append("@addr").append(System.lineSeparator());
			sb.append("A = M").append(System.lineSeparator());
			sb.append("M = D").append(System.lineSeparator());

		}
		return sb.toString();
	}

	private String staticSegmentAssembly(Command command, int index) {
		StringBuffer sb = new StringBuffer();
		if (command == Command.C_PUSH) {

			sb.append("// push static ").append(index).append(System.lineSeparator());

			sb.append("@").append(fileName).append(".").append(index).append(System.lineSeparator());
			sb.append("D = M").append(System.lineSeparator());

			sb.append("@SP").append(System.lineSeparator());
			sb.append("A = M").append(System.lineSeparator());
			sb.append("M = D").append(System.lineSeparator());

			sb.append("@SP").append(System.lineSeparator());
			sb.append("M = M + 1").append(System.lineSeparator());

		} else if (command == Command.C_POP) {

			sb.append("// pop static ").append(index).append(System.lineSeparator());

			sb.append("@SP").append(System.lineSeparator());
			sb.append("M = M - 1").append(System.lineSeparator());

			sb.append("@SP").append(System.lineSeparator());
			sb.append("A = M").append(System.lineSeparator());
			sb.append("D = M").append(System.lineSeparator());

			sb.append("@").append(fileName).append(".").append(index).append(System.lineSeparator());
			sb.append("M = D").append(System.lineSeparator());

		}
		return sb.toString();
	}

	private String tempSegmentAssembly(Command command, int index) {
		StringBuffer sb = new StringBuffer();
		if (command == Command.C_PUSH) {

			sb.append("// push temp ").append(index).append(System.lineSeparator());

			sb.append("@").append(index).append(System.lineSeparator());
			sb.append("D = A").append(System.lineSeparator());

			sb.append("@5").append(System.lineSeparator());
			sb.append("D = A + D").append(System.lineSeparator());

			sb.append("@addr").append(System.lineSeparator());
			sb.append("M = D").append(System.lineSeparator());
			sb.append("A = M").append(System.lineSeparator());
			sb.append("D = M").append(System.lineSeparator());

			sb.append("@SP").append(System.lineSeparator());
			sb.append("A = M").append(System.lineSeparator());
			sb.append("M = D").append(System.lineSeparator());

			sb.append("@SP").append(System.lineSeparator());
			sb.append("M = M + 1").append(System.lineSeparator());

		} else if (command == Command.C_POP) {

			sb.append("// pop temp ").append(index).append(System.lineSeparator());

			sb.append("@").append(index).append(System.lineSeparator());
			sb.append("D = A").append(System.lineSeparator());

			sb.append("@5").append(System.lineSeparator());
			sb.append("D = A + D").append(System.lineSeparator());

			sb.append("@addr").append(System.lineSeparator());
			sb.append("M = D").append(System.lineSeparator());

			sb.append("@SP").append(System.lineSeparator());
			sb.append("M = M - 1").append(System.lineSeparator());

			sb.append("@SP").append(System.lineSeparator());
			sb.append("A = M").append(System.lineSeparator());
			sb.append("D = M").append(System.lineSeparator());

			sb.append("@addr").append(System.lineSeparator());
			sb.append("A = M").append(System.lineSeparator());
			sb.append("M = D").append(System.lineSeparator());

		}
		return sb.toString();
	}

	private String pointerSegmentAssembly(Command command, int index) {
		StringBuffer sb = new StringBuffer();
		String key;

		if (index == 0) {
			key = "THIS";
		} else {
			key = "THAT";
		}

		if (command == Command.C_PUSH) {
			sb.append("// push pointer ").append(index).append(System.lineSeparator());

			sb.append("@").append(key).append(System.lineSeparator());
			// sb.append("A = M").append(System.lineSeparator());
			sb.append("D = M").append(System.lineSeparator());

			sb.append("@SP").append(System.lineSeparator());
			sb.append("A = M").append(System.lineSeparator());
			sb.append("M = D").append(System.lineSeparator());

			sb.append("@SP").append(System.lineSeparator());
			sb.append("M = M + 1").append(System.lineSeparator());

		} else if (command == Command.C_POP) {
			sb.append("// pop pointer ").append(index).append(System.lineSeparator());

			sb.append("@SP").append(System.lineSeparator());
			sb.append("M = M - 1").append(System.lineSeparator());

			sb.append("@SP").append(System.lineSeparator());
			sb.append("A = M").append(System.lineSeparator());
			sb.append("D = M").append(System.lineSeparator());

			sb.append("@").append(key).append(System.lineSeparator());
			// sb.append("A = M").append(System.lineSeparator());
			sb.append("M = D").append(System.lineSeparator());

		}
		return sb.toString();
	}

	private String twoOperandAssembly(String operator) {
		StringBuffer sb = new StringBuffer();

		sb.append("@SP").append(System.lineSeparator());
		sb.append("M = M - 1").append(System.lineSeparator());
		sb.append("A = M").append(System.lineSeparator());
		sb.append("D = M").append(System.lineSeparator());

		sb.append("@SP").append(System.lineSeparator());
		sb.append("M = M - 1").append(System.lineSeparator());
		sb.append("A = M").append(System.lineSeparator());
		sb.append("M = M ").append(operator).append(" D").append(System.lineSeparator());

		sb.append("@SP").append(System.lineSeparator());
		sb.append("M = M + 1").append(System.lineSeparator());

		return sb.toString();
	}

	// gt, eq, lt logical
	private String booleanAssembly(String operator) {
		StringBuffer sb = new StringBuffer();
		// TODO: boolean operations
		sb.append("@SP").append(System.lineSeparator());
		sb.append("M = M - 1").append(System.lineSeparator());
		sb.append("A = M").append(System.lineSeparator());
		sb.append("D = M").append(System.lineSeparator());

		sb.append("@SP").append(System.lineSeparator());
		sb.append("M = M - 1").append(System.lineSeparator());
		sb.append("A = M").append(System.lineSeparator());
		sb.append("D = M - D").append(System.lineSeparator());

		sb.append("@true").append(".").append(logicalCounter).append(System.lineSeparator());

		switch (operator.toUpperCase()) {
		case ("EQ"):
			sb.append("D ; JEQ").append(System.lineSeparator());
			break;
		case ("GT"):
			sb.append("D ; JGT").append(System.lineSeparator());
			break;
		case ("LT"):
			sb.append("D ; JLT").append(System.lineSeparator());
			break;
		}

		sb.append("(false").append(".").append(logicalCounter).append(")").append(System.lineSeparator());
		sb.append("@SP").append(System.lineSeparator());
		sb.append("A = M").append(System.lineSeparator());
		sb.append("M = 0").append(System.lineSeparator());
		sb.append("@end").append(".").append(logicalCounter).append(System.lineSeparator());
		sb.append("0 ; JMP").append(System.lineSeparator());

		sb.append("(true").append(".").append(logicalCounter).append(")").append(System.lineSeparator());
		sb.append("@SP").append(System.lineSeparator());
		sb.append("A = M").append(System.lineSeparator());
		sb.append("M = -1").append(System.lineSeparator());

		sb.append("(end").append(".").append(logicalCounter).append(")").append(System.lineSeparator());
		sb.append("@SP").append(System.lineSeparator());
		sb.append("M = M + 1").append(System.lineSeparator());

		logicalCounter++;

		return sb.toString();
	}

	private String oneOperandAssembly(String operator) {
		StringBuffer sb = new StringBuffer();

		sb.append("@SP").append(System.lineSeparator());
		sb.append("M = M - 1").append(System.lineSeparator());
		sb.append("A = M").append(System.lineSeparator());
		sb.append("M = ").append(operator).append("M").append(System.lineSeparator());

		sb.append("@SP").append(System.lineSeparator());
		sb.append("M = M + 1").append(System.lineSeparator());

		return sb.toString();
	}

}
