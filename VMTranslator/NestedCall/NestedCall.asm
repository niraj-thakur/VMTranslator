// function Sys.init 0
(Sys.init)
// push constant 4000
@4000
D = A
@SP
A = M
M = D
@SP
M = M + 1
// pop pointer 0
@SP
M = M - 1
@SP
A = M
D = M
@THIS
M = D
// push constant 5000
@5000
D = A
@SP
A = M
M = D
@SP
M = M + 1
// pop pointer 1
@SP
M = M - 1
@SP
A = M
D = M
@THAT
M = D
// call Sys.main 0
@Sys.main$ret.0
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
@Sys.main
0 ; JMP
(Sys.main$ret.0)

// pop temp 1
@1
D = A
@5
D = A + D
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
// label Sys.init$LOOP
(Sys.init$LOOP)
// goto Sys.init$LOOP
@Sys.init$LOOP
0;JMP
// function Sys.main 5
(Sys.main)
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
// push constant 0
@0
D = A
@SP
A = M
M = D
@SP
M = M + 1
// pop local 2
@LCL
D = M
@2
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
// pop local 3
@LCL
D = M
@3
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
// pop local 4
@LCL
D = M
@4
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
// push constant 4001
@4001
D = A
@SP
A = M
M = D
@SP
M = M + 1
// pop pointer 0
@SP
M = M - 1
@SP
A = M
D = M
@THIS
M = D
// push constant 5001
@5001
D = A
@SP
A = M
M = D
@SP
M = M + 1
// pop pointer 1
@SP
M = M - 1
@SP
A = M
D = M
@THAT
M = D
// push constant 200
@200
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
// push constant 40
@40
D = A
@SP
A = M
M = D
@SP
M = M + 1
// pop local 2
@LCL
D = M
@2
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
// push constant 6
@6
D = A
@SP
A = M
M = D
@SP
M = M + 1
// pop local 3
@LCL
D = M
@3
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
// push constant 123
@123
D = A
@SP
A = M
M = D
@SP
M = M + 1
// call Sys.add12 1
@Sys.add12$ret.1
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
@Sys.add12
0 ; JMP
(Sys.add12$ret.1)

// pop temp 0
@0
D = A
@5
D = A + D
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
// push local 2
@LCL
D = M
@2
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
// push local 3
@LCL
D = M
@3
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
// push local 4
@LCL
D = M
@4
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
// function Sys.add12 0
(Sys.add12)
// push constant 4002
@4002
D = A
@SP
A = M
M = D
@SP
M = M + 1
// pop pointer 0
@SP
M = M - 1
@SP
A = M
D = M
@THIS
M = D
// push constant 5002
@5002
D = A
@SP
A = M
M = D
@SP
M = M + 1
// pop pointer 1
@SP
M = M - 1
@SP
A = M
D = M
@THAT
M = D
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
// push constant 12
@12
D = A
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
