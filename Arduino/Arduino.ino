#include <SoftwareSerial.h>

#include <Servo.h>

Servo s[12];

char buffer[24];

void setup(){
  Serial.begin(9600);
  
  for(int i = 0; i < 12; i++){
    s[i].attach(i+1);
    //s[i].write(0); //issue?
  }
  //s[0].write(0);
  
 
 
  //while(!Serial){}
  
  //s[0].write(180);
}

void loop(){
  s[0].write(180);
  //Serial.write("h");

  if(Serial.available() == -1){
    //s[0].write(180);
  }
/*
  while(Serial.available() > 24){
    for(int i = 0; i < 24; i++){
      buffer[i] = Serial.read();
    }

    Serial.write(buffer);

    s[0].write(180);
  }
  */
}

int hex2dec(char c) { //c is the character that was buffered
  if(c == '0'){
    return 0;
  } else if(c == '1'){
    return 1;    
  } else if(c == '2'){
    return 2;    
  } else if(c == '3'){
    return 3;    
  } else if(c == '4'){
    return 4;    
  } else if(c == '5'){
    return 5;    
  } else if(c == '6'){
    return 6;    
  } else if(c == '7'){
    return 7;    
  } else if(c == '8'){
    return 8;    
  } else if(c == '9'){
    return 9;    
  } else if(c == 'A'){
    return 10;    
  } else if(c == 'B'){
    return 11;    
  } else if(c == 'C'){
    return 12;    
  } else if(c == 'D'){
    return 13;    
  } else if(c == 'E'){
    return 14;    
  } else if(c == 'F'){
    return 15;    
  }
}

