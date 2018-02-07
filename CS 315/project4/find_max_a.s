main:
    mov sp, #256 /* assumes RAM of 64 words */
    sub sp, sp, #4
    mov r0, #2
    str r0, [sp]
    sub sp, sp, #4
    mov r0, #2
    str r0, [sp]
    sub sp, sp, #4
    mov r0, #37
    str r0, [sp]
    sub sp, sp, #4
    mov r0, #9
    str r0, [sp]
    sub sp, sp, #4
    mov r0, #4
    str r0, [sp]

    mov r0, sp
    mov r1, #5

find_max_a:
    sub sp, sp, #4
    str r4, [sp]
    sub sp, sp, #4
    str r5, [sp]
    mov r2, #0 // i = 0
    ldr r3, [r0] // max
    bl loop

end_max:
    b end_max

loop:
    cmp r2, r1
    beq done
    ldr r4, [r0]
    add r0, r0, #4
    cmp r4, r3
    movgt r3, r4
    add r2, r2, #1
    b loop
done:
    ldr r5, [sp]
    add sp, sp, #4
    ldr r4, [sp]
    add sp, sp, #4
    mov r0, r3
    bx lr
