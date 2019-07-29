// SP = 256 
@256
D = A
@SP
M = D
// call Sys.init
@Sys.init$ret.0
D = A
@SP
A = M
M = D
@SP
M = M + 1
@LCL
D = M
@SP
A = M
M = D
@SP
M = M + 1
@ARG
D = M
@SP
A = M
M = D
@SP
M = M + 1
@THIS
D = M
@SP
A = M
M = D
@SP
M = M + 1
@THAT
D = M
@SP
A = M
M = D
@SP
M = M + 1
@5
D = A
@SP
D = M - D
@ARG
M = D
@SP
D = M
@LCL
M = D
@Sys.init
0 ; JMP
(Sys.init$ret.0)

// function Main.fibonacci 0
(Main.fibonacci)
// push argument 0
@ARG
D = M
@0
D = D + A
@addr
M = D
@addr
A = M
D = M
@SP
A = M
M = D
@SP
M = M + 1
// push constant 2
@2
D = A
@SP
A = M
M = D
@SP
M = M + 1
// lt
@SP
M = M - 1
A = M
D = M
@SP
M = M - 1
A = M
D = M - D
@true.0
D ; JLT
(false.0)
@SP
A = M
M = 0
@end.0
0 ; JMP
(true.0)
@SP
A = M
M = -1
(end.0)
@SP
M = M + 1
// if-goto Main.fibonacci$IF_TRUE
@SP
M = M - 1
@SP
A = M
D = M
@Main.fibonacci$IF_TRUE
D ; JNE
// goto Main.fibonacci$IF_FALSE
@Main.fibonacci$IF_FALSE
0;JMP
// label Main.fibonacci$IF_TRUE
(Main.fibonacci$IF_TRUE)
// push argument 0
@ARG
D = M
@0
D = D + A
@addr
M = D
@addr
A = M
D = M
@SP
A = M
M = D
@SP
M = M + 1
// return
@LCL
D = M
@endFrame
M = D
@5
D = A
@endFrame
A = M - D
D = M
@retAddr
M = D
@SP
M = M - 1
A = M
D = M
@ARG
A = M
M = D
@ARG
D = M + 1
@SP
M = D
@endFrame
A = M - 1
D = M
@THAT
M = D
@2
D = A
@endFrame
A = M - D
D = M
@THIS
M = D
@3
D = A
@endFrame
A = M - D
D = M
@ARG
M = D
@4
D = A
@endFrame
A = M - D
D = M
@LCL
M = D
@retAddr
A = M
0 ; JMP
// label Main.fibonacci$IF_FALSE
(Main.fibonacci$IF_FALSE)
// push argument 0
@ARG
D = M
@0
D = D + A
@addr
M = D
@addr
A = M
D = M
@SP
A = M
M = D
@SP
M = M + 1
// push constant 2
@2
D = A
@SP
A = M
M = D
@SP
M = M + 1
// sub
@SP
M = M - 1
A = M
D = M
@SP
M = M - 1
A = M
M = M - D
@SP
M = M + 1
// call Main.fibonacci 1
@Main.fibonacci$ret.1
D = A
@SP
A = M
M = D
@SP
M = M + 1
@LCL
D = M
@SP
A = M
M = D
@SP
M = M + 1
@ARG
D = M
@SP
A = M
M = D
@SP
M = M + 1
@THIS
D = M
@SP
A = M
M = D
@SP
M = M + 1
@THAT
D = M
@SP
A = M
M = D
@SP
M = M + 1
@6
D = A
@SP
D = M - D
@ARG
M = D
@SP
D = M
@LCL
M = D
@Main.fibonacci
0 ; JMP
(Main.fibonacci$ret.1)

// push argument 0
@ARG
D = M
@0
D = D + A
@addr
M = D
@addr
A = M
D = M
@SP
A = M
M = D
@SP
M = M + 1
// push constant 1
@1
D = A
@SP
A = M
M = D
@SP
M = M + 1
// sub
@SP
M = M - 1
A = M
D = M
@SP
M = M - 1
A = M
M = M - D
@SP
M = M + 1
// call Main.fibonacci 1
@Main.fibonacci$ret.2
D = A
@SP
A = M
M = D
@SP
M = M + 1
@LCL
D = M
@SP
A = M
M = D
@SP
M = M + 1
@ARG
D = M
@SP
A = M
M = D
@SP
M = M + 1
@THIS
D = M
@SP
A = M
M = D
@SP
M = M + 1
@THAT
D = M
@SP
A = M
M = D
@SP
M = M + 1
@6
D = A
@SP
D = M - D
@ARG
M = D
@SP
D = M
@LCL
M = D
@Main.fibonacci
0 ; JMP
(Main.fibonacci$ret.2)

// add
@SP
M = M - 1
A = M
D = M
@SP
M = M - 1
A = M
M = M + D
@SP
M = M + 1
// return
@LCL
D = M
@endFrame
M = D
@5
D = A
@endFrame
A = M - D
D = M
@retAddr
M = D
@SP
M = M - 1
A = M
D = M
@ARG
A = M
M = D
@ARG
D = M + 1
@SP
M = D
@endFrame
A = M - 1
D = M
@THAT
M = D
@2
D = A
@endFrame
A = M - D
D = M
@THIS
M = D
@3
D = A
@endFrame
A = M - D
D = M
@ARG
M = D
@4
D = A
@endFrame
A = M - D
D = M
@LCL
M = D
@retAddr
A = M
0 ; JMP
// function Sys.init 0
(Sys.init)
// push constant 4
@4
D = A
@SP
A = M
M = D
@SP
M = M + 1
// call Main.fibonacci 1
@Main.fibonacci$ret.3
D = A
@SP
A = M
M = D
@SP
M = M + 1
@LCL
D = M
@SP
A = M
M = D
@SP
M = M + 1
@ARG
D = M
@SP
A = M
M = D
@SP
M = M + 1
@THIS
D = M
@SP
A = M
M = D
@SP
M = M + 1
@THAT
D = M
@SP
A = M
M = D
@SP
M = M + 1
@6
D = A
@SP
D = M - D
@ARG
M = D
@SP
D = M
@LCL
M = D
@Main.fibonacci
0 ; JMP
(Main.fibonacci$ret.3)

// label Sys.init$WHILE
(Sys.init$WHILE)
// goto Sys.init$WHILE
@Sys.init$WHILE
0;JMP
