main:
    mov sp, #256 /* assumes RAM of 64 words */
    mov r0, #6
    bl fib_rec_a

end_main:
    b end_main

fib_rec_a:
    sub sp, sp, #4
    str r4, [sp]
    sub sp, sp, #4
    str r5, [sp]
    sub sp, sp, #4
    str r6, [sp]
    sub sp, sp, #4
    str lr, [sp]

    cmp r0, #2 // n >= 2
    beq fib_rec_one 
    bgt fib_rec_one 
    cmp r0, #1 // n == 1
    moveq r0, #1 // return 1
    cmp r0, #0// n == 0
    moveq r0, #0 // return 0

    b done

fib_rec_one:
    mov r6, r0 // store a copy of n on stack
    sub r0, r0, #1 // n - 1 
    bl fib_rec_a 
    mov r4, r0 // store return(n-1)
    sub r0, r6, #2// n - 2
    bl fib_rec_a
    mov r5, r0// store return(n-2)
    add r0, r4, r5 // add both returns together

done:
    ldr lr, [sp]
    add sp, sp, #4
    ldr r6, [sp]
    add sp, sp, #4
    ldr r5, [sp]
    add sp, sp, #4
    ldr r4, [sp]
    add sp, sp, #4
    
    bx lr
