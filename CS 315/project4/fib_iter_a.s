main:
    mov sp, #256 /* assumes RAM of 64 words */
    mov r0, #19
    bl fib_iter_a

end_main:
    b end_main

fib_iter_a:
    //r0 = n
    sub sp, sp, #4
    str r4, [sp]
    sub sp, sp, #4
    str r5, [sp]
    mov r1, #0 // one before = 0
    mov r2, #1 // two before = 1 
    mov r3, #0 // current = 0
    mov r4, #1 // i = 0

cmp r0, #0 //if(n == 0)
beq done
cmp r0, #1//if(n == 1)
beq else

loop:
    cmp r4, r0
    bgt done
    add r3, r1, r2 // current = one_before + two_before
    mov r2, r1 //two_before = one_before
    mov r1, r3 //one_before = current
    add r4, r4, #1 // i++
    b loop

else:
    mov r3, #1
    beq done

done:
    ldr r5, [sp]
    add sp, sp, #4
    ldr r4, [sp]
    add sp, sp, #4
    mov r0, r3
    bx lr
