
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
};


//Prints the usage of NT
void print_usage(void)
{
    printf("usage: nt [-b width] [-r start,end] value\n");
    printf("  width is 4, 8, 16, 32 (default)\n");
    printf("  start is 0 to 31, end is 0 to 31\n");
    printf("  value can be binary, hexadecimal, unsigned or signed decimal.\n");
    return;
}

int toBinaryFromUnsignedInt(struct conversions *conv){
    int tmpX = conv->unInt;//temp variable
    // char bin[32];
    
    int counter =0;
    // conv->bin[0] = '0';
    for (int i=31; i>=0; i--) {
        // printf("%d", i);
        int expCounter;
        unsigned int total = 1;
        for(expCounter = i; expCounter > 0; expCounter--){
            total *= 2;
        }
        if(total<=tmpX) {
            tmpX -= total;
            conv->bin[counter] = '1';
            counter++;
            // printf("%d\n", total);
        }else{
            conv->bin[counter] = '0';
            counter++;
            // printf("%d\n", total);
        }
        
    }
    // printf("%s\n", bin);
    // for (int i=1; i<=32; i++) {
    //     printf("%c", conv->bin[i-1]);
    // }
    // printf("\n");
    // for (int i=1; i<=32; i++) {
    //     printf("%c",conv->bin[i-1]);
    //     if(i%4==0){
    //         printf(" ");
    //     }
    // }
    // printf("\n");
    return 0;
}

unsigned int fromBinaryToUnsignedInt(char *binary, int flag, struct conversions * conv){
    
    int len;
    int len2;
    int count;
    unsigned total = 0;
    if (flag == 0){ // when 0b is at the begenning 
        binary+= 2;
        len = (int) strlen(binary)-1 ;
        len2 =len+1;
        count = 0;
    }else if(flag == 1){ // used by chartoUnsigned Int
        len = (int) strlen(binary)-1 ;
        len2 = len+1;
        count = 0;
    }else if(flag ==3){ // for from HextoBinary
        // binary+= 1;
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
           // printf("%u\n",btotal);
            total += btotal;
        }
        // printf("%d : %c : %d\n",count, binary[count], len);
        len--;
        count++;
    }
    
    conv->unInt = total;

    return total;
}

unsigned int fromHexToUnsignedInt(char *hex, struct conversions *conv){
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
    return fromBinaryToUnsignedInt(hexaDecimal, 3, conv);
    // return 0;
}

unsigned int charToUnsignedInt(char *decimal, struct conversions *conv){
    int len2 = (int)strlen(decimal);
    // the locaiton in with the single digit numver is found
    //for example the 10 100 1000.

    unsigned long long unInt;
    unInt=0;
    unsigned int neg=0;

    for(int j =0; j < strlen(decimal); j++){// goes through each number in the char decimal
        char c = decimal[j];
        int i;
        int b;
        int len = sizeof(char)*8;
        char a[len];
        char normal[8];
        
        for(i = 0; i < len;i++){
            b = c & 0b1;
            a[i] = b+'0';
            c = c >> 1;
        }
        int j =0;
        for(i = len-1; i >=0;  i--){
            // printf("%c", a[i]);
            normal[j] = a[i];
            j++;
        }

        // here normal is a noraml binary number
        unsigned int newNum=0;
        newNum = (fromBinaryToUnsignedInt(normal, 1, conv));
        
        if(newNum == 45){// this is the case if it is negative
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

    if(unInt >= 4294967296){
        printf("The number has to be less than 32 bits: %llu\n", unInt);
        print_usage();
        exit(1);  
    }

    conv->unInt = (int)unInt;
    return unInt;
}

int valadateBinary(char *binary){
    binary+=2;
    if(strlen(binary) > 32){
        printf("the binary number is more than 32 bytes\n");
        print_usage();
        exit(1);
    }
    int count = 0;
    
    while(count < strlen(binary)){
        if(!(binary[count] == '0' || binary[count] == '1')){
            // printf("%s\n", binary);
            printf("Error with the Binary number, %s\n", binary);
            print_usage();
            exit(1);
        }
        count++;
    }

    return 0;
}

int valadateHex(char *hexaDecimal){
    hexaDecimal += 2;

    if(strlen(hexaDecimal) > 8){
        printf("the hexaDecimal number is more than 32 bytes\n");
        print_usage();
        exit(1);
    }

    int count = 0;
    
    while(count < strlen(hexaDecimal)){
        if((hexaDecimal[count] >= '0' && hexaDecimal[count] <= '9')){
            
        }else if((hexaDecimal[count] >= 'A' && hexaDecimal[count] <= 'F')){
            
        }else{
            // printf("%s\n", hexaDecimal);
            printf("Error with the HexDecimal number: %s\n", hexaDecimal);
            print_usage();
            exit(1);
        }

        count++;
    }

    return 0;
}

int valadateInt(char *integer){
    for(int i=0; i < strlen(integer); i++){
        if((integer[i] < '0' || integer[i] > '9') && integer[i] != '-'){
            printf("An Error occured: %s\n", integer);
            print_usage();
            exit(1);
        }
    }
    return 0;
}

int valadateBitWidth(struct conversions *conv){
    if(conv->bWidth == 4 || 
        conv->bWidth == 8 || 
        conv->bWidth == 16 || 
        conv->bWidth == 32){
        return 0;
    }else{
        printf("Error wihle parsing -b\n");
        print_usage();
        exit(1);
    }

    return 1;
}

int parsR(char *range, struct conversions *conv){

    int len = (int) strlen(range);

    char left[4];
    char right[4];

    int count = 0; 
    int rCount = 0;

    while (range[count] != ','){
        left[count] = range[count];
        count++;
        if(count == len){
            printf("Error while parsing -r\n");
            print_usage();
            exit(1);
        }
    }
    // count = 0;
    while(count < len){
        // printf("%s\n",range[count]);
        if(range[count] >= '0' && range[count] <='9'){
            right[rCount] = range[count];
            rCount ++;
        }else if((range[count] <= '0' && range[count] >='9') || range[count]!= ','){
            printf("Error while parsing -r 2\n");
            print_usage();
            exit(1);
        }
        count++;
    }

    conv->rangeLeft = atoi(left);
    conv->rangeRight = atoi(right);

    if(conv->rangeLeft <0){
        printf("Error while parsing -r\n");
        exit(1);
    }

    if(conv->rangeRight > 32){
        printf("Error while parsing -r\n");
        exit(1);
    }

    if(conv->rangeLeft >= conv->rangeRight){
        printf("Error while parsing -r\n");
        exit(1);
    }

    return 0;
}

int changeRangeAndBitWidth(struct conversions *conv){
    char temp[33];
    // char binary[33];

    int count =0;

    if(conv->rangeLeft != 0 && conv->rangeRight != 32){
        for (int i=(32-conv->rangeRight); i<=32-conv->rangeLeft; i++){
            if(conv->rangeRight != 32){
                temp[count] = conv->bin[i-1];
            }else{
                temp[count] = conv->bin[i];
            }
            count++;
        }
        int c = 0;
        for (int i=(0); i<32; i++){
            if(i < 32-count){
                // binary[i] ='0';
                conv->bin[i] = '0';
            }else{
                // printf("%c\n", temp[c]);
                // binary[i] = temp[c];
                conv->bin[i] = temp[c];
                c++;
            }
        }
    } else if(conv->bWidth != 32){
        for (int i=(32 - conv->bWidth + 1); i<=32; i++) {
            temp[count] = conv->bin[i-1];
            count++;
        }
        // printf("%s\n", temp);
        int c = 0;

        for (int i=(0); i<32; i++){
            if(i < 32-count){
                // binary[i] ='0';
                conv->bin[i] = '0';
            }else{
                // printf("%c\n", temp[c]);
                // binary[i] = temp[c];
                conv->bin[i] = temp[c];
                c++;
            }
        }
    }
    fromBinaryToUnsignedInt(conv->bin, 1, conv);
    return 0;
}

void print(struct conversions *conv){

    unsigned int m16 = 0xFFFF0000;
    unsigned int m8  = 0xFFFFFF00;
    unsigned int m4  = 0xFFFFFFF0;
    unsigned int newSigned;
    unsigned int mask;

    for (int i=(32 - conv->bWidth  + 1); i<=32; i++) {
        printf("%c",conv->bin[i-1]);
        if(i%4==0){
            printf(" ");
        }
    }
    printf(" (base 2)\n");

    printf("0b");
    for (int i=(32 - conv->bWidth + 1); i<=32; i++) {

        printf("%c",conv->bin[i-1]);
    }
    printf("(base 2)\n");
    if(conv->bWidth == 32){
        printf("0x%08X (base 16)\n", conv->unInt);
    }else if(conv->bWidth == 16){
        printf("0x%04X (base 16)\n", conv->unInt);
        mask = m16;
    }else if(conv->bWidth == 8){
        printf("0x%02X (base 16)\n", conv->unInt);
        mask = m8;
    }else if(conv->bWidth == 4){
        printf("0x%X (base 16)\n", conv->unInt);
        mask = m4;
    }
    printf("%u (base 10 unsigned)\n", conv->unInt);


    if (((conv->unInt >> (conv->bWidth-1)) & 0b1)) {
        newSigned = mask | conv->unInt;
        printf("%d (base 10 signed)\n", newSigned);
    } else {
        printf("%d (base 10 signed)\n", conv->unInt);
    }  




  
    

}

int classifer(int argc, char **argv, struct conversions *conv){
    if(argc > 6){//checks to see how many argments there are.
        printf("To many arguments\n");
        print_usage();
        exit(1);
    }

    //sets the defualt bit width
    conv->bWidth = 32;
    conv->rangeLeft = 0;
    conv->rangeRight = 32;
    int lc = 1; //Location Identifer
    // printf("%s\n", argv[1]);
    
    // printf("%d\n", (int) strlen(argv[1]));


    if(argv[1][0] == '-' && strlen(argv[1]) == 2){
        if(argv[1][1] == 'b'){
            conv->bWidth = atoi(argv[2]);
            lc +=2;
            if(argv[3][0] == '-' && argv[3][1] == 'r' && strlen(argv[3]) == 2){
                parsR(argv[4], conv);
                lc +=2;
            }
        }else if(argv[1][1] == 'r'){
            parsR(argv[2], conv);
            if(argv[3][0] == '-' && argv[3][1] == 'b' && strlen(argv[3]) == 2){
                conv->bWidth = atoi(argv[2]);
                lc +=2;
            }
            lc +=2;
        }else if((argv[1][1] >= 'a' && argv[1][1] <= 'z') || (argv[1][1] >= 'A' && argv[1][1] <= 'Z')){
            printf("Error\n");
            exit(1);
        }
    }

    valadateBitWidth(conv);

    //The above code deals with -b and -r while also change the location lc, so that that will always 
    // point to the data that is needed to convert.

    //THe below code classifes what the number is and then calls the correct functions
    // needed to finish the translation

    if((argv[lc][0] == '-' || argv[lc][0] != '0')){ // decimal is given in char
        // printf("decimal is given\n");
        valadateInt(argv[lc]);//this just makes sure there are no letters.
        unsigned int unInt = charToUnsignedInt(argv[lc], conv);
        if(argv[lc][0] == '-' && (int)unInt > 0){//This solved the problem of -2147483649
            printf("An error occured: %s\n", argv[lc]);
            print_usage();
            exit(1);
        } if(unInt == 0){
            printf("An error occured: %s\n", argv[lc]);
            print_usage();
            exit(1);
        }
        toBinaryFromUnsignedInt(conv);
    }else if(argv[lc][0] == '0' && argv[lc][1] == 'b'){// binary is given in char
        // printf("binary is given\n");
        valadateBinary(argv[lc]);
        fromBinaryToUnsignedInt(argv[lc], 0, conv);
        toBinaryFromUnsignedInt(conv);
    }else if(argv[lc][0] == '0' && argv[lc][1] == 'x'){// hexadecimal is given
        // printf("hexdecimal is given\n");
        valadateHex(argv[lc]);
        fromHexToUnsignedInt(argv[lc], conv);
        // printf("->%u\n", conv.unInt);
        // unsignedIntToHexadecimal(conv->unInt, conv);
        toBinaryFromUnsignedInt(conv);
    }else if(argv[lc][0] == '0' && strlen(argv[lc]) == 1) {// the case in which the input is 0.
        charToUnsignedInt(argv[lc], conv);
        toBinaryFromUnsignedInt(conv);
    }else{
        printf("Error: %s\n", argv[lc]);
        exit(1);
    }

    //the below funciton that will limit range of a number
    //It will also do the same for the bitWidth
    changeRangeAndBitWidth(conv);

    //finishes the program by calling the print.
    print(conv);
    return 0;
}

int main(int argc, char **argv){
    struct conversions conv;

    classifer(argc, argv, &conv);

    return 0;
}
