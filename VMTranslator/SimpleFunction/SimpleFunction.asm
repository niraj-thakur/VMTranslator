// function SimpleFunction.test 2
(SimpleFunction.test)
// push constant 0
@0
D = A
@SP
A = M
M = D
@SP
M = M + 1
// pop local 0
@LCL
D = M
@0
D = D + A
@addr
M = D
@SP
M = M - 1
@SP
A = M
D = M
@addr
A = M
M = D
@SP
M = M + 1
// push constant 0
@0
D = A
@SP
A = M
M = D
@SP
M = M + 1
// pop local 1
@LCL
D = M
@1
D = D + A
@addr
M = D
@SP
M = M - 1
@SP
A = M
D = M
@addr
A = M
M = D
@SP
M = M + 1
// push local 0
@LCL
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
// push local 1
@LCL
D = M
@1
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
// not
@SP
M = M - 1
A = M
M = !M
@SP
M = M + 1
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
// push argument 1
@ARG
D = M
@1
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
0 ; JMP
