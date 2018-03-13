//
//  main.c
//  InClass
//
//  Created by Jay Garrett Cruikshank on 8/23/17.
//  Copyright © 2017 Jay Garrett Cruikshank. All rights reserved.
//

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <limits.h>

struct conversions{
    char bin[33];
    unsigned int unInt;
    int bWidth;
    int rangeLeft;
    int rangeRight;
}conv;

int toBinaryFromUnsignedInt(unsigned x){
    int tmpX = x;
    // char bin[32];
    
    
    int counter =0;
    conv.bin[0] = '0';
    for (int i=31; i>=0; i--) {
        // printf("%d", i);
        int expCounter;
        unsigned int total = 1;
        for(expCounter = i; expCounter > 0; expCounter--){
            total *= 2;
        }
        if(total<=tmpX) {
            tmpX -= total;
            conv.bin[counter] = '1';
            counter++;
            // printf("%d\n", total);
        }else{
            conv.bin[counter] = '0';
            counter++;
            // printf("%d\n", total);
        }
        
    }
    // printf("%s\n", bin);
    for (int i=1; i<=32; i++) {
        printf("%c", conv.bin[i-1]);
    }
    printf("\n");
    for (int i=1; i<=32; i++) {
        printf("%c",conv.bin[i-1]);
        if(i%4==0){
            printf(" ");
        }
    }
    printf("\n");
    
    
    return 0;
}

unsigned int fromBinaryToUnsignedInt(char *binary, int torf){
    
    int len;
    int len2;
    int count;
    unsigned total = 0;
    if (torf == 0){ // when 0b is at the begenning 
        binary+= 2;
        len = (int) strlen(binary)-1 ;
        len2 =len+1;
        count = 0;
    }else if(torf == 1){ // used by charbits
        len = (int) strlen(binary)-3 ;
        len2 = len+1;
        count = 0;
    }else if(torf ==3){ // for from HextoBinary
        binary+= 1;
        len = (int) strlen(binary)-1 ;
        len2 =len+1;
        count = 0;
    }
    
    while(count < len2){
        if(binary[count] != '0' ){
            unsigned int btotal = 1;
            for(int i = len; i > 0; i--){
                btotal*=2;
            }
//            printf("%u\n",btotal);
            total += btotal;
        }
        // printf("%d : %c : %d\n",count, binary[count], len);
        len--;
        count++;
    }
    
    conv.unInt = total;
    return total;
}

unsigned int fromHexToUnsignedInt(char *hex){
    int i = 0;
    int len = (int) strlen(hex)-2;
    hex += 2;
    char hexaDecimal[65];
    while(i<len){
        switch (hex[i]) {
            case '0':
                strcat(hexaDecimal, "0000");
                break;
            case '1':
                strcat(hexaDecimal, "0001");
                break;
            case '2':
                strcat(hexaDecimal, "0010");
                break;
            case '3':
                strcat(hexaDecimal, "0011");
                break;
            case '4':
                strcat(hexaDecimal, "0100");
                break;
            case '5':
                strcat(hexaDecimal, "0101");
                break;
            case '6':
                strcat(hexaDecimal, "0110");
                break;
            case '7':
                strcat(hexaDecimal, "0111");
                break;
            case '8':
                strcat(hexaDecimal, "1000");
                break;
            case '9':
                strcat(hexaDecimal, "1001");
                break;
            case 'A':
                strcat(hexaDecimal, "1010");
                break;
            case 'B':
                strcat(hexaDecimal, "1011");
                break;
            case 'C':
                strcat(hexaDecimal, "1100");
                break;
            case 'D':
                strcat(hexaDecimal, "1101");
                break;
            case 'E':
                strcat(hexaDecimal, "1110");
                break;
            case 'F':
                strcat(hexaDecimal, "1111");
                break;
            default:
                printf("Wrong HexDecimal Format\n");
                exit(1);
        }
        i++;
    }
    
    // printf("%d\n",fromBinaryToUnsignedInt(hexaDecimal, 3));
    
    // printf("%s\n", hexaDecimal);
    return fromBinaryToUnsignedInt(hexaDecimal, 3);
    // return 0;
}

unsigned int charToUnsignedInt(char *decimal){
    int len2 = (int)strlen(decimal);
    unsigned int unInt;
    unInt=0;
    unsigned int neg=0;
    
    for(int j =0; j < strlen(decimal); j++){
        char c = decimal[j];
        int i;
        int b;
        int len = sizeof(char)*8;
        char a[len];
        char normal[len];
        
        for(i = 0; i < len;i++){
            b = c & 0b1;
            a[i] = b+'0';
            c = c >> 1;
        }
        int j =0;
        for(i = len-1; i >=0;  i--){
//            printf("%c", a[i]);
            normal[j] = a[i];
            j++;
        }
//        printf("\n");
        
        
        unsigned int newNum=0;
        newNum = (fromBinaryToUnsignedInt(normal,1));
        
        
        if(newNum == 45){
            newNum -= '0';
            neg = newNum;
            
        }else{
            newNum -= '0';
            
            int multiply = 1;
            for(int p = len2-1; p > 0; p-- ){
                multiply *=10;
            }
//            printf("%d %u\n", multiply, newNum);
            unInt += (newNum * multiply);
        }
        len2--;
        
    }
    if(neg != 0){
        unInt = neg - unInt +3;
    }
//    printf("%u\n", unInt);
    conv.unInt = unInt;
    return unInt;
}

int unsignedIntToHexadecimal(unsigned int unInt){
    toBinaryFromUnsignedInt(unInt);
        // printf("%u\n", unInt);
    char hexaDecimal[16];
    printf("--%d\n", strlen(conv.bin));
    // for(int i = 0; i < strlen(conv.bin); i+=4){
    //     char temp[4];
    //     for(int j = 0; j < 4; j++){
    //         temp[j] = conv.bin[i + j];
    //     }
        
    //     switch (temp) {
    //         case "0000":
    //             strcat(hexaDecimal, "0");
    //             break;
    //         case '1':
    //             strcat(hexaDecimal, "0001");
    //             break;
    //         case '2':
    //             strcat(hexaDecimal, "0010");
    //             break;
    //         case '3':
    //             strcat(hexaDecimal, "0011");
    //             break;
    //         case '4':
    //             strcat(hexaDecimal, "0100");
    //             break;
    //         case '5':
    //             strcat(hexaDecimal, "0101");
    //             break;
    //         case '6':
    //             strcat(hexaDecimal, "0110");
    //             break;
    //         case '7':
    //             strcat(hexaDecimal, "0111");
    //             break;
    //         case '8':
    //             strcat(hexaDecimal, "1000");
    //             break;
    //         case '9':
    //             strcat(hexaDecimal, "1001");
    //             break;
    //         case 'A':
    //             strcat(hexaDecimal, "1010");
    //             break;
    //         case 'B':
    //             strcat(hexaDecimal, "1011");
    //             break;
    //         case 'C':
    //             strcat(hexaDecimal, "1100");
    //             break;
    //         case 'D':
    //             strcat(hexaDecimal, "1101");
    //             break;
    //         case 'E':
    //             strcat(hexaDecimal, "1110");
    //             break;
    //         case 'F':
    //             strcat(hexaDecimal, "1111");
    //             break;
    //     }


    // }
    // printf("%s\n", hexaDecimal);
    printf("--%u\n", conv.unInt);






    return 0;
}

int classifer(char **argv){
    
    printf("%s\n", argv[1]);
    
    if(argv[1][0] == '-'){
        if(argv[1][1] == 'b'){
            conv.bWidth = atoi(argv[2]);
        }
        else if(argv[1][1] == 'r'){
            
        }
    }

    if((argv[1][0] == '-' || argv[1][0] != '0'){
        unsigned int unInt = charToUnsignedInt(argv[1]);
        toBinaryFromUnsignedInt(unInt);
        
    }else if(argv[1][0] == '0' && argv[1][1] == 'b'){
        printf("binary is given\n");
        printf("%u\n", fromBinaryToUnsignedInt(argv[1], 0));
    }else if(argv[1][0] == '0' && argv[1][1] == 'x'){
        printf("hexdecimal is given\n");
        conv.unInt = fromHexToUnsignedInt(argv[1]);
        // printf("->%u\n", conv.unInt);
        unsignedIntToHexadecimal(conv.unInt);
    }
    //else{
    //        printf("a zero is given.\n");
    //    }
    
    return 0;
}



int main(int argc, char **argv){
    
    classifer(argv);
    
    return 0;
}
