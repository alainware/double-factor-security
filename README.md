# Double Factor Security

This is the final project for the Programming Languages course (TC-2006) at Monterrey Institute of Technology and Higher Education Campus Querétaro.

As the title implies, this project is related with the topic of cybersecurity. In general terms, this repository shows an implementation of two cybersecurity techniques which are Data Encryption and Steganography. For the part of Data Encryption we are using one of the most popular standards on the industry, which is AES-128, and for the part of Steganography, we are using image Steganography.

Authors:

- Irving Alain Aguilar Pérez - A01703171
- Randy Hazael Jiménez Arriaga - A01251333

## Index

1. [Introduction](#Introduction)
2. [AES-128](#AES-128)
3. [Steganography](#Steganography)
4. [Cryptography vs Steganography](#Cryptography-vs-Steganography)
5. [Java Implementation](#Java-Implementation)
6. [Paradigm](#Paradigm)
7. [Conclusion](#Conclusion)
8. [References](#References)

## Introduction

Today we live in a totally globalized world due to the different technological advances which help humans to communicate from different parts all over the planet in a matter of seconds. Information security plays a vital role in such communication, because at present, data travels through many channels most of which are insecure and can expose users' information to malicious attacks.

According to GeeksForGeeks(2020), Information Security is not only about securing information from unauthorized access. **Information Security** is the practice of preventing unauthorized access, use, disclosure, disruption, modification, inspection, recording or destruction of information.

An example of information when talking about computer systems could be the password of a social media account, the data that is stored on a mobile phone such as biometric data or your credit card details, between others. Thus, Information Security spans so many research areas like Cryptography, Mobile Computing, Cyber Forensics, Online Social Media, etc.

Most of the advances on the area of Information Security were developed during World War I and World War II to keep safe sensitive information. During World War II, Alan Turing was the one who successfully decrypted the Enigma Machine which was used by Germans to encrypt warfare data.

The 3 golden principles when developing an Information Security program are:

- **Confidentiality**: information is not disclosed to unauthorized individuals, entities and process.
- **Integrity**: maintaining accuracy and completeness of data. Data cannot be edited in an unauthorized way.
- **Availability**: information must be available when needed.

Some other principles to take in account on Information Security programs  are the following:

- **Non repudiation**: one party cannot deny receiving a message or a transaction, nor can the other party deny sending a message or transaction.
- **Authenticity**: verify that users are who they say they are and that each input arriving at destination is from a trusted source.
- **Accountability**: it should be possible to trace actions of an identity uniquely to that entity.

An important part of Information Security is Cryptography. Cryptography is the heart and soul of all the security protocols used on a daily basis to secure information.

According to Richards, K. (2020), **Cryptography** is a method of protecting information and communications through the use of codes, so that only for whom the information in intended can read and process it. The prefix "crypt" means "hidden" or "vault", and the suffix "graphy" stands for writing.

In terms of computer science, **Cryptography** refers to secure information and communication techniques derived from mathematical concepts and a set of rule-based calculations called algorithms, to transform messages in ways that are hard to decrypt. These deterministic algorithms are used for cryptograhic key generation, digital signing, verification to protect data privacy, web browsing on the internet, and confidential communications such as credit card transactions and email.

Security protocols basically make use of cryptosystems to be able to offer confidentiality, integrity and availability.

![](https://i.imgur.com/EX0HJVh.png)


## AES-128

### Basic concepts

- Private key: a set of characters that is only known by a particular entity.
- Plain text: text of information that is encrypted by an algorithm.
- Encrypted text: text that is originated by encrypting plain text with a key using an encryption algorithm.
- Encryption algorithm: process that converts plain text into encrypted text by applying multiple operations and a key. The algorithm is reversible.
- Decryption algorithm: process that converts encrypted text into plain text by applying multiple inverse operations and a key.
- Cryptography: set of techniques or procedures that alter information symbols without modifying the content, converting the modified information in a set of symbols without content for the parts that do not have the techniques.
- Block encryption: processing/encrypting the information in fixed-size blocks.

### Symmetric Encryption

A symmetric encryption algorithm (also referred to as private key), is known for using a single key to encrypt and decrypt the information that is transmitted between sender and addressee through an insecure channel.

It is based on applying an algorithm to a plain text using a unique and shared key by the issuer and the receiver to generate ciphertext. The original message is recovered when applying to ciphertext the same algorithm (to the inverse) with the same unique key.

![](https://i.imgur.com/xYq7HWK.png)


<img src="https://miro.medium.com/max/3372/1*bbCyiW35hBU3GiaiF4Qcmw.png" alt="Symmetric Encryption">

#### The strength of symmetric encryption

It depends on two factors: 

1. It must achieve diffusion and confusion making use of transpose operations, substitution, sums, multiplications and XOR.
    - **Diffusion**: hides the relationship between plaintext and ciphertext. It is achieved using permutation and transposition. Each bit of the plaintext and the key influences all the bits of the ciphertext, thus achieving a great "diffusion".
    - **Confusion**: it hides the relationship between the clear text and the secret key. The confusion is achieved using substitution and alternatively using other operations (+, x, and XOR).
3. The key length must be longer than the block to be encrypted and long enough so it cannot be decrypted by brute force during the lifetime of the key.


### Advanced Encryption Standard

**Advanced Encryption Standard (AES)** is one of the most popular standards for symmetric encryption currently available. It is publicly accessible, and it is the type of encryption that NSA uses to secure documents with the "top secret" classification.

Its success story began in 1997, when the NIST (National Institute of Standards and Technology) officially began searching for a successor to the old DES encryption standard. An algorithm called "Rijndael" developed by two Belgian cryptographers Joan Daemen and Vincent Rijmen excelled in security as well as in performance and flexibility. Rijndael appeared on top of several competitors and the new AES encryption standard based on Rijndael was announced in 2001.

AES is based on various substitutions, permutations and linear transformations, each executed on 16 byte blocks of data - hence the term "block cipher". These operations are repeated several times, called "rounds". During each round, a unique circular key is calculated from the encryption key and incorporated into the calculations.

Based on the AES block structure, changing a single bit, either in the key, or in the clear text block, results in a completely new different block of ciphered text, being this a clear advantage over traditional stream cipher.

Strictly speaking, AES is not exactly Rijndael, since Rijndael allows a greater range of block size and key length. AES has a fixed block size of 128 (performing 10 rounds), 192 (performing 12 rounds), or 256 bits (performing 14 rounds) while Rijndael can be specified by a key that is a multiple of 32 bits, with a minimum of 128 bits and a maximum of 256 bits.

### Techniques used by the encryption algorithm

The techniques used by AES are made up of confusion and diffusion, together forming a robust encryption. These techniques are described as follows:

#### keyExpansion

First, we need to take in account that:
- AES operates on a 4x4 byte matrix, called state. 
- The number of rounds performed by the algorithm depends on the length of the key. 
- On each round, a new generated subkey is formed based on the master key (original key).
- In order to generate each subkey, an expansion function is used, which makes use of keyword rotation, a subBytes function and XOR function.


#### SubBytes (Confusion)

In this step, a non-linear substitution is performed where each byte is replaced with another according to a lookup table (each byte on the state matrix is replaced with the value on the equivalent position in the Rijndael S-Box table).

<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/AES-SubBytes.svg/320px-AES-SubBytes.svg.png" alt="SubBytes Function">

**Rijndael S-Box table** (C language example)

```c=
unsigned char s_box[256] =  
 {0x63 ,0x7c ,0x77 ,0x7b ,0xf2 ,0x6b ,0x6f ,0xc5 ,0x30 ,0x01 ,0x67 ,0x2b ,0xfe ,0xd7 ,0xab ,0x76
 ,0xca ,0x82 ,0xc9 ,0x7d ,0xfa ,0x59 ,0x47 ,0xf0 ,0xad ,0xd4 ,0xa2 ,0xaf ,0x9c ,0xa4 ,0x72 ,0xc0
 ,0xb7 ,0xfd ,0x93 ,0x26 ,0x36 ,0x3f ,0xf7 ,0xcc ,0x34 ,0xa5 ,0xe5 ,0xf1 ,0x71 ,0xd8 ,0x31 ,0x15
 ,0x04 ,0xc7 ,0x23 ,0xc3 ,0x18 ,0x96 ,0x05 ,0x9a ,0x07 ,0x12 ,0x80 ,0xe2 ,0xeb ,0x27 ,0xb2 ,0x75
 ,0x09 ,0x83 ,0x2c ,0x1a ,0x1b ,0x6e ,0x5a ,0xa0 ,0x52 ,0x3b ,0xd6 ,0xb3 ,0x29 ,0xe3 ,0x2f ,0x84
 ,0x53 ,0xd1 ,0x00 ,0xed ,0x20 ,0xfc ,0xb1 ,0x5b ,0x6a ,0xcb ,0xbe ,0x39 ,0x4a ,0x4c ,0x58 ,0xcf
 ,0xd0 ,0xef ,0xaa ,0xfb ,0x43 ,0x4d ,0x33 ,0x85 ,0x45 ,0xf9 ,0x02 ,0x7f ,0x50 ,0x3c ,0x9f ,0xa8
 ,0x51 ,0xa3 ,0x40 ,0x8f ,0x92 ,0x9d ,0x38 ,0xf5 ,0xbc ,0xb6 ,0xda ,0x21 ,0x10 ,0xff ,0xf3 ,0xd2
 ,0xcd ,0x0c ,0x13 ,0xec ,0x5f ,0x97 ,0x44 ,0x17 ,0xc4 ,0xa7 ,0x7e ,0x3d ,0x64 ,0x5d ,0x19 ,0x73
 ,0x60 ,0x81 ,0x4f ,0xdc ,0x22 ,0x2a ,0x90 ,0x88 ,0x46 ,0xee ,0xb8 ,0x14 ,0xde ,0x5e ,0x0b ,0xdb
 ,0xe0 ,0x32 ,0x3a ,0x0a ,0x49 ,0x06 ,0x24 ,0x5c ,0xc2 ,0xd3 ,0xac ,0x62 ,0x91 ,0x95 ,0xe4 ,0x79
 ,0xe7 ,0xc8 ,0x37 ,0x6d ,0x8d ,0xd5 ,0x4e ,0xa9 ,0x6c ,0x56 ,0xf4 ,0xea ,0x65 ,0x7a ,0xae ,0x08
 ,0xba ,0x78 ,0x25 ,0x2e ,0x1c ,0xa6 ,0xb4 ,0xc6 ,0xe8 ,0xdd ,0x74 ,0x1f ,0x4b ,0xbd ,0x8b ,0x8a
 ,0x70 ,0x3e ,0xb5 ,0x66 ,0x48 ,0x03 ,0xf6 ,0x0e ,0x61 ,0x35 ,0x57 ,0xb9 ,0x86 ,0xc1 ,0x1d ,0x9e
 ,0xe1 ,0xf8 ,0x98 ,0x11 ,0x69 ,0xd9 ,0x8e ,0x94 ,0x9b ,0x1e ,0x87 ,0xe9 ,0xce ,0x55 ,0x28 ,0xdf
 ,0x8c ,0xa1 ,0x89 ,0x0d ,0xbf ,0xe6 ,0x42 ,0x68 ,0x41 ,0x99 ,0x2d ,0x0f ,0xb0 ,0x54 ,0xbb ,0x16
 };
```

#### ShiftRows (Diffusion)

The rows of the table are shifted to the left.

<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/6/66/AES-ShiftRows.svg/1200px-AES-ShiftRows.svg.png" alt="ShiftRows Function" width="400px" height="160px">

#### AddRoundKey (Confusion)

This makes a change to the bits, adding the key with the current state (bitwise XOR operation).

<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/ad/AES-AddRoundKey.svg/2560px-AES-AddRoundKey.svg.png" alt="AddRoundKey Function" width="400px" height="300px">

#### MixColumns (Confusion)

A substitution that alters each byte in a column as a function of all of the bytes in the column. Each column from the state matrix is multiplied by a constant polynomial c(x).

<img src="https://upload.wikimedia.org/wikipedia/commons/7/76/AES-MixColumns.svg" alt="MixColumns Function" width="400px" height="200px">



## Steganography

Steganography (from the Greek steganos, which means "concealed" or "covered", and graphia, which means "writing") is the discipline that involves the study and application of techniques that allow objects or messages to to be concealed inside other types of data known as carriers in order to make their existence imperceptible.

### Image Steganography (Text to Image)

There are two types of image steganography. The Image to Image is where an image is concealed inside another image. The Text to Image inserts text within an image. We will be working with Text to Image.

<img
src="https://media.geeksforgeeks.org/wp-content/uploads/2-72.png" alt="Steganography">

The better quality and the higher resolution an image has, the easier and more effective embedding information in it is. However, it is important to note that should the image file be converted to a different format, the hidden information could be altered or even deleted.

To extract the message, the recipient of the image must know the algorithm being used so they can know which pixels must be selected.

### Least Significant Bit (LSB)

Let's consider that for a PC an image is no more than an array of numbers displaying the different colors and light intensities in each pixel. The most effective method for image steganography is the Least Significant Bit method, or LSB. In this method, for each pixel that makes up the image, its least significant bit (the bit in the far right of a byte) is used to store the information to hide. This way, the result is an image virtually identical to the original but with a hidden message within.

<img
src="https://www.researchgate.net/profile/Ari-Muzakir-2/publication/317306868/figure/fig3/AS:503640696016896@1497088707548/Example-of-Least-Significant-Bit-LSB-In-figure-4-seen-bits-LSB-at-1-pixel-of-color.png" alt="LSB">

The secret message must be converted to a binary format based on its ASCII code. For instance, if we have a secret message "A", and an image with pixels {4, 134, 42, 78, 200, 33, 76, 57}. The secret message in binary is 01000001, and in the following table we have the binary of the image's pixels and the message embedding:

| Image Pixels | Binary Pixels | Binary Embedded | Embedded Pixels |
|:------------:|:-------------:|:---------------:|:---------------:|
|      4       |   00000100    |  0000010**0**   |        4        |
|     134      |   10000110    |  1000011**1**   |       135       |
|      42      |   00101010    |  0010101**0**   |       42        |
|      78      |   01001110    |  0100111**0**   |       78        |
|     200      |   11001000    |  1100100**0**   |       200       |
|      33      |   00100001    |  0010000**0**   |       32        |
|      76      |   01001100    |  0100110**0**   |       76        |
|      57      |   00111001    |  0011100**1**   |       57        |

The following diagram shows the process of embedding text in an image by converting its characters into binary, and stores them into the image's LSB.

![](https://i.imgur.com/cPWox7u.png)

And to extract the text the opposite is done, by getting the last bit of each byte and using them to rebuild the hidden message.

![](https://i.imgur.com/zinUZPN.png)

## Cryptography vs Steganography

These are both methods that can hide or protect sensitive data. The difference between this two is that cryptography hides the meaning of a message without concealing the message itself while steganography hides the existence of a message without altering its content.

In other words, an encrypted message can be found but not easily understood, while an embedded message can be understood but hard to find. Both these methods offer a decent layer of protection for confidential data, but come with their own setbacks.

|     |               Cryptography                |               Stenography               |
| --- |:-----------------------------------------:|:---------------------------------------:|
| 1   | Converts a message to a non readable form |   Hides the existence of the message    |
| 2   |                 Is known                  |                Is hidden                |
| 3   |         Alters the message itself         |       Does not alter the message        |
| 4   |      The final result is cipher text      |     The final result is stego media     |
| 5   |   The data is hard to get if discovered   | The data is easy to get once discovered |


However, these two are not mutually exclusive, and, as we will see in the implementation, they can even complement each other. By encrypting a message and then hiding it, it is possible to trip up any attempt at a security breach even further.

## Java Implementation

One of the biggest features the Java language has is the use of threads. With them it is possible to divide a complicated set of instructions into multiple and more manageable tasks of their own. By using threads, it is possible to divide the encryption and decryption process into a more efficient one and protect the information even further.

The project works through multiple classes, with each one of them fulfilling a specific role in order to get the desired result.

![](https://i.imgur.com/ZJyeHc8.png)

### Steganography.java

This class takes care of embedding and extracting text into a user selected image. The class receives the parameters for its methods from the Main class.

#### Method embedText

|        |               |
|:------:|:-------------:|
| Input  | Image, String |
| Output |     Image     |

This method uses LSB to embed a String into a specified image. It accomplishes this by taking each character of the String, converting them into ASCII, getting its binary value from the ASCII and placing it in the last digit of a pixel.

```java=
StringBuilder word = new StringBuilder(text);
//Add a delimiter for the message
word.append("}");
text = word.toString();
int bitMsk = 0x00000001;	// bit mask to get the digits
int bit;				// integer that represents the ASCII of a character
int x = 0;				// starting x pixel
int y = 0;				// starting y pixel
int txtLen = text.length();
for(int i = 0; i < txtLen; i++) {			
    bit = (int) text.charAt(i);		// get the ASCII of a character at position i
    for(int j = 0; j < 8; j++) {
        int flag = bit & bitMsk;	// get 1 digit from the character
		if(flag == 1) {	
            if(x < image.getWidth()) {
                image.setRGB(x, y, image.getRGB(x, y) | 0x00000001); 	// store the 1 bit into a pixel's last digit
                x++;
            } else {
                x = 0;
                y++;
                image.setRGB(x, y, image.getRGB(x, y) | 0x00000001); 	// store the 1 bit into a pixel's last digit
            }
		} else {	
            if(x < image.getWidth()) {
                image.setRGB(x, y, image.getRGB(x, y) & 0xFFFFFFFE);	// store the 0 bit into a pixel's last digit
                x++;
            } else {
                x = 0;
			    y++;
			    image.setRGB(x, y, image.getRGB(x, y) & 0xFFFFFFFE);	// store the 0 bit into a pixel's last digit
            }
		}
		bit = bit >> 1;				// get the next digit
    }			
}
		
// save the image which contains the text to a new image file
try {
    File outFile = new File("textEmbedded.png");	
    ImageIO.write(image, "png", outFile);	
} catch (IOException e) {
			
}		
return image;
```

Note: The String the method receives is already encrypted. Also, a character "}" is added at the end of the String as a delimiter for the extractText method.

#### Method extractText

|        |               |
|:------:|:-------------:|
| Input  | Image, String |
| Output |    String     |

This method reverses what embedText does by extracting the embedded String character by character, sending it for decryption, and printing the result.

```java=
/**
* [START] Variables for extractText method
*/
// Integer variables
int bitMsk = 0x00000001;	// bit mask to get the digits
int x = 0;					// starting x pixel
int y = 0;					// starting y pixel
int flag;
// Char variables
List<Character> c = new ArrayList<Character>();	// ArrayList to store the message extracted from the image character by character
// String variables
StringBuilder word = new StringBuilder(""); // String to append the characters of variable 'c' in order to build the message
String decode = ""; // String to parse the 'word' StringBuilder object
String decData = ""; //String to store the decoded message generated by AES decryption
/**
* [END] Variables for extractText method
*/
                
//A single digit is extracted for comparison
int bit = 0;
			
// 8 digits form a character
for(int j = 0; j < 8; j++) {				
    if(x < image.getWidth()) {
        flag = image.getRGB(x, y) & bitMsk;	// get the last digit of the pixel
        x++;
    } else {
        x = 0;
        y++;
        flag = image.getRGB(x, y) & bitMsk;	// get the last digit of the pixel
    }
				
    // store the extracted digits into an integer as ASCII
    if(flag == 1) {					
        bit = bit >> 1;	
        bit = bit | 0x80;
    } else {					
        bit = bit >> 1;
    }				
}
c.add((char) bit);	// Parse the ASCII to characters
word.append(c.get(0)); // Append characters to the word StringBuilder
                     
//The rest of the string is extracted
int i = 0;
while(word.toString().charAt(i) != '}') {	
    for(int j = 0; j < 8; j++) {				
		if(x < image.getWidth()) {
            flag = image.getRGB(x, y) & bitMsk;
            x++;
		} else {
            x = 0;
            y++;
            flag = image.getRGB(x, y) & bitMsk;
        }
				
		if(flag == 1) {					
            bit = bit >> 1;	
            bit = bit | 0x80;
		} 
		else {					
            bit = bit >> 1;
		}				
	}
	c.add((char) bit);
	word.append(c.get(i));
    i++;
}
                
//Delete the duplicate character
word.deleteCharAt(0);
//Delete the delimiter
word.deleteCharAt(word.length()-1);
                
decode = word.toString(); // Convert StringBuilder object to String type
//decData = aesDec.decryptAES(decode); // Decrypt message using AES
decData = MultiAES.decrypt(decode, uKey); // Decrypt message using AES
JOptionPane.showMessageDialog(null, "Message was decrypted successfully!\nThe message is:\n " + decData);
```

#### Method checkKey

|        |         |
| ------ |:-------:|
| Input  | String  |
| Output | Boolean |

This method validates that the encryption key is 16 characters long.

```java=
if (uKey.length() == 16) {
    return true;
} else {
    return false;
}
```

### MultiAES.java

This class acts as a sort of "middle man" between the Main class and the encryption and decryption synchronized threads by formatting the received String into multiple arrays, creating threads for the specified action to take, and sending the substrings to those threads.

#### Method addToArray

|        |                  |
| ------ |:----------------:|
| Input  | String[], String |
| Output |     String[]     |


This method separates a given String by splitting words separated by blankspace and putting them in an array of Strings.

```java=
arr = Arrays.copyOf(arr, arr.length + 1);
arr[arr.length - 1] = string; // Assign 40 to the last element
return arr;
```

#### Method encrypt

|        |                |
| ------ |:--------------:|
| Input  | String, String |
| Output |     String     |

This method initializes a number of threads that will encrypt each word of the message. The threads are sent the message and an encryption key.

```java=
/*
[START] Encrypting loop
*/

// Step 1. Convert the message to a String array

String[] messageArray = msg.split(" ");

// Step 2. Create a new String Array for storing the encrypted words

String[] encryptedMessageArray = {};

// Step 3. Encrypt each word inside messageArray

AESEncrypt encryptedWords[] = new AESEncrypt[messageArray.length];
Thread threads[] = new Thread[messageArray.length];
for (int i = 0; i < messageArray.length; i++) {
    encryptedWords[i] = new AESEncrypt(uKey, messageArray[i]);
    threads[i] = new Thread(encryptedWords[i]);
    threads[i].start();
    threads[i].join();
    encryptedMessageArray = addToArray(encryptedMessageArray, encryptedWords[i].getEncryptedValue());
}

// Step 4. Append the ecnrypted strings to a StringBuilder

StringBuilder finalMessage = new StringBuilder("");
for (String string : encryptedMessageArray) {
    finalMessage.append(string);
}
return finalMessage.toString();
/*
[END] Encrypting loop
*/
```

![](https://i.imgur.com/CuzzOHs.png)


#### Method decrypt

|        |                |
| ------ |:--------------:|
| Input  | String, String |
| Output |     String     |

This method initializes a number of threads that will decrypt each word of the message. The threads are sent the encrypted message and its encryption key.

```java=
/*
[START] Decrypting loop
*/
        
// Step 1. Convert the message to a String Array

String[] encMessageArray = msg.split("==");

// Step 2. Create a new String Array for storing the decrypted words

String[] decryptedMessageArray = {};

// Step 3. Encrypt each word inside messageArray

AESDecrypt decryptedWords[] = new AESDecrypt[encMessageArray.length];
Thread threads[] = new Thread[encMessageArray.length];
for (int i = 0; i < encMessageArray.length; i++) {
    decryptedWords[i] = new AESDecrypt(uKey, encMessageArray[i]);
    threads[i] = new Thread(decryptedWords[i]);
    threads[i].start();
    threads[i].join();
    decryptedMessageArray = addToArray(decryptedMessageArray, decryptedWords[i].getDecryptedStrMessage());
}

// Step 4. Append the decrypted strings to a StringBuilder

StringBuilder finalDecryptedMessage = new StringBuilder("");

for (int i = 0; i < decryptedMessageArray.length; i++) {
    if (i == decryptedMessageArray.length - 1) {
        finalDecryptedMessage.append(decryptedMessageArray[i] + ".");
    } else {
        finalDecryptedMessage.append(decryptedMessageArray[i] + " ");
    }
}
return finalDecryptedMessage.toString();
/*
[END] Decrypting loop
*/
```

![](https://i.imgur.com/5BurY9F.png)



### AESEncrypt.java

This is a Runnable class that's called by a thread. Here a Key datatype is generated and the information is sent for encryption.

#### Method encryptAES

|        |        |
| ------ |:------:|
| Input  | String |
| Output | String |

This method calls for the manual encryption of a message.

```java=
Key key = generateKey();
byte [] encData = ManAES.encrypt(messageData.getBytes("UTF-8"), key.getEncoded());
Base64.Encoder encoder = Base64.getEncoder();
encryptedValue = encoder.encodeToString(encData);
```

#### Method generateKey

|        |      |
| ------ |:----:|
| Input  | None |
| Output | Key  |

This method encrypts the encryption key for further protection.

```java=
Key key = new SecretKeySpec(uKey, ALGORITHM);
return key;
```

### AESDecrypt.java

This is a Runnable class that's called by a thread. Here the key used for encryption is made into a Key datatype and the message is sent for decryption.

#### Method decryptAES

|        |        |
| ------ |:------:|
| Input  | String |
| Output | String |

This method calls for the manual decryption of an encrypted message.

```java=
Key key = generateKey();
Base64.Decoder decoder = Base64.getDecoder();
byte [] decodedValue = decoder.decode(encryptedData);
byte [] decData = ManAES.decrypt(decodedValue, key.getEncoded());
decryptedStrMessage  = new String(decData);
```

#### Method generateKey

|        |        |
| ------ |:------:|
| Input  | String |
| Output | String |

This method encrypts the encryption key for further protection.

```java=
Key key = generateKey();
byte [] encData = ManAES.encrypt(messageData.getBytes("UTF-8"), key.getEncoded());
Base64.Encoder encoder = Base64.getEncoder();
encryptedValue = encoder.encodeToString(encData);
```

### ManAES.java



This class is where all the magic happens. Here we are implementing AES functions to encrypt and decrypt a given message by using a user-defined key.

#### Method manXOR

|        |                |
| ------ |:--------------:|
| Input  | byte[], byte[] |
| Output |     byte[]     |

This is a helper function that performs an XOR operation between two byte arrays. The output of the function is the resulting byte array.

```java=
		byte[] out = new byte[a.length];
		for (int i = 0; i < a.length; i++) {
			out[i] = (byte) (a[i] ^ b[i]);
		}
		return out;
```

#### Method generateSubkeys

|        |                |
| ------ |:--------------:|
| Input  | byte[] |
| Output |     byte[][]     |

This function is used to generate a subkey for each of the AES rounds.

```java=
		byte[][] cpy = new byte[nBlock * (nRounds + 1)][4];
		int i = 0;
		while (i < nKey) {
			cpy[i][0] = key[i * 4];
			cpy[i][1] = key[i * 4 + 1];
			cpy[i][2] = key[i * 4 + 2];
			cpy[i][3] = key[i * 4 + 3];
			i++;
		}
		i = nKey;
		while (i < nBlock * (nRounds + 1)) {
			byte[] aux = new byte[4];
			for(int k = 0;k<4;k++)
				aux[k] = cpy[i-1][k];
			if (i % nKey == 0) {
				aux = subWord(rotateWord(aux));
				aux[0] = (byte) (aux[0] ^ (rcon[i / nKey] & 0xff));
			} else if (nKey > 6 && i % nKey == 4) {
				aux = subWord(aux);
			}
			cpy[i] = manXOR(cpy[i - nKey], aux);
			i++;
		}
		return cpy;
```


#### Method subWord

|        |                |
| ------ |:--------------:|
| Input  | byte[] |
| Output |     byte[]     |

This is a helper function used by the generateSubkeys function. It receives a byte array and performs a substitution using sbox lookup table, storing byte values in another byte array and finally returning the resulting array as the output. 

```java=
		byte[] cpy = new byte[in.length];
		for (int i = 0; i < cpy.length; i++) {
			cpy[i] = (byte) (sbox[in[i] & 0x000000ff] & 0xff);
		}
		return cpy;
```

#### Method rotateWord

|        |                |
| ------ |:--------------:|
| Input  | byte[] |
| Output |     byte[]     |

This is a helper function used by the generateSubkeys function. It receives a byte array and performs a rotation to the bytes in the array moving them a step to the left. The output of the function is the rotated byte array.

```java=
		byte[] cpy = new byte[inBytes.length];
		cpy[0] = inBytes[1];
		cpy[1] = inBytes[2];
		cpy[2] = inBytes[3];
		cpy[3] = inBytes[0];
		return cpy;
```

#### Method addRoundKey

|        |                |
| ------ |:--------------:|
| Input  | byte[][], byte [][], int |
| Output |     byte[][]     |

This function is used to perform a sum between the state and the cycle key using arithmetic operations (binary sum mod 2 - XOR). This means that each bit in the input is added to the corresponding bit in the loop key, saving mod2 of the result in the state.

```java=
		byte[][] cpy = new byte[state.length][state[0].length];
		for (int c = 0; c < nBlock; c++) {
			for (int l = 0; l < 4; l++) {
				cpy[l][c] = (byte) (state[l][c] ^ w[round * nBlock + c][l]);
			}
		}
		return cpy;
```

#### Method subBytes

|        |                |
| ------ |:--------------:|
| Input  | byte[][] |
| Output |     byte[][]   |

This function replaces each byte of the state with another byte, depending on the key received as a parameter. The substitutions are presented in a table, generally known as the Rijndael box. This table consists of 256-byte substitutions contained in a 16x16 array.

```java=
		byte[][] cpy = new byte[state.length][state[0].length];
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < nBlock; col++) {
				cpy[row][col] = (byte) (sbox[(state[row][col] & 0x000000ff)] & 0xff);
			}
		}
		return cpy;
```

#### Method invSubBytes

|        |                |
| ------ |:--------------:|
| Input  | byte[][] |
| Output |     byte[][]   |

This function replaces each byte of the state with another byte, depending on the key received as a parameter. The substitutions are presented in a table, generally known as the Rijndael box. This table consists of 256-byte substitutions contained in a 16x16 array.

Same as subBytes, but using inv_sbox.

```java=
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < nBlock; col++) {
				state[row][col] = (byte)(inv_sbox[(state[row][col] & 0x000000ff)]&0xff);
			}
		}
		return state;
```

#### Method shiftRows

|        |                |
| ------ |:--------------:|
| Input  | byte[][] |
| Output |     byte[][]   |

This function rotates the rows to the left. As bits are traversing to the left, they reappear on the right side.

```java=
		byte[] t = new byte[4];
		for (int r = 1; r < 4; r++) {
			for (int c = 0; c < nBlock; c++) {
				t[c] = state[r][(c + r) % nBlock];
			}
			for (int c = 0; c < nBlock; c++) {
				state[r][c] = t[c];
			}
		}
		return state;
```

#### Method invShiftRows

|        |                |
| ------ |:--------------:|
| Input  | byte[][] |
| Output |     byte[][]   |

This function is the inverse to shiftRows.

```java=
		byte[] t = new byte[4]; 
		for (int r = 1; r < 4; r++) {
			for (int c = 0; c < nBlock; c++) {
				t[(c + r)%nBlock] = state[r][c];
			}
			for (int c = 0; c < nBlock; c++) {
				state[r][c] = t[c];
			}
		}
	return state;
```

#### Method mixColumns

|        |                |
| ------ |:--------------:|
| Input  | byte[][] |
| Output |     byte[][]   |

This function is used to calculate the dot product between binary arrays (bytes). After the dot product is done, the results are added and reduced so that they can be entered in one byte. In the end, the XOR operation is performed between results.

```java=
		int[] sp = new int[4];
		byte b02 = (byte)0x02, b03 = (byte)0x03;
		for (int c = 0; c < 4; c++) {
			sp[0] = byteMul(b02, s[0][c]) ^ byteMul(b03, s[1][c]) ^ s[2][c]  ^ s[3][c];
			sp[1] = s[0][c]  ^ byteMul(b02, s[1][c]) ^ byteMul(b03, s[2][c]) ^ s[3][c];
			sp[2] = s[0][c]  ^ s[1][c]  ^ byteMul(b02, s[2][c]) ^ byteMul(b03, s[3][c]);
			sp[3] = byteMul(b03, s[0][c]) ^ s[1][c]  ^ s[2][c]  ^ byteMul(b02, s[3][c]);
			for (int i = 0; i < 4; i++) {
				s[i][c] = (byte)(sp[i]);
			}
		}
		return s;
```


#### Method invmixColumns

|        |                |
| ------ |:--------------:|
| Input  | byte[][] |
| Output |     byte[][]   |

This method is the inverse of mixColumns.

```java=
		int[] sp = new int[4];
		byte b02 = (byte)0x0e, b03 = (byte)0x0b, b04 = (byte)0x0d, b05 = (byte)0x09;
		for (int c = 0; c < 4; c++) {
			sp[0] = byteMul(b02, s[0][c]) ^ byteMul(b03, s[1][c]) ^ byteMul(b04,s[2][c])  ^ byteMul(b05,s[3][c]);
			sp[1] = byteMul(b05, s[0][c]) ^ byteMul(b02, s[1][c]) ^ byteMul(b03,s[2][c])  ^ byteMul(b04,s[3][c]);
			sp[2] = byteMul(b04, s[0][c]) ^ byteMul(b05, s[1][c]) ^ byteMul(b02,s[2][c])  ^ byteMul(b03,s[3][c]);
			sp[3] = byteMul(b03, s[0][c]) ^ byteMul(b04, s[1][c]) ^ byteMul(b05,s[2][c])  ^ byteMul(b02,s[3][c]);
			for (int i = 0; i < 4; i++) {
			s[i][c] = (byte)(sp[i]);
			}
		}
		return s;
```


#### Method byteMul

|        |                |
| ------ |:--------------:|
| Input  | byte |
| Output |     byte   |

This is a helper function to the mixColums method. It is used to calculate the dot product between binary arrays (bytes).

```java=
		byte aByte = a, bByte = b, r = 0, t;
		while (aByte != 0) {
			if ((aByte & 1) != 0) {
				r = (byte) (r ^ bByte);
			}
			t = (byte) (bByte & 0x80);
			bByte = (byte) (bByte << 1);
			if (t != 0) {
				bByte = (byte) (bByte ^ 0x1b);
			}
			aByte = (byte) ((aByte & 0xff) >> 1);
		}
		return r;
```

#### Method encryptBlock

|        |                |
| ------ |:--------------:|
| Input  | byte[] |
| Output |     byte[] |

Method used for encrypting a block of data.

```java=
		byte[] cpy = new byte[in.length];
		byte[][] state = new byte[4][nBlock];
		for (int i = 0; i < in.length; i++) {
			state[i / 4][i % 4] = in[i%4*4+i/4];
		}
		state = addRoundKey(state, w, 0);
		for (int round = 1; round < nRounds; round++) {
			state = subBytes(state);
			state = shiftRows(state);
			state = mixColumns(state);
			state = addRoundKey(state, w, round);
		}
		state = subBytes(state);
		state = shiftRows(state);
		state = addRoundKey(state, w, nRounds);
		for (int i = 0; i < cpy.length; i++) {
			cpy[i%4*4+i/4] = state[i / 4][i%4];
		}
		return cpy;
```

#### Method decryptBlock

|        |                |
| ------ |:--------------:|
| Input  | byte[] |
| Output |     byte[] |

Method used for decrypting a block of data.

```java=
		byte[] cpy = new byte[in.length];
		byte[][] state = new byte[4][nBlock];
		for (int i = 0; i < in.length; i++) {
			state[i / 4][i % 4] = in[i%4*4+i/4];
		}
		state = addRoundKey(state, w, nRounds);
		for (int round = nRounds-1; round >=1; round--) {
			state = invSubBytes(state);
			state = invShiftRows(state);
			state = addRoundKey(state, w, round);
			state = invmixColumns(state);
			
		}
		state = invSubBytes(state);
		state = invShiftRows(state);
		state = addRoundKey(state, w, 0);

		for (int i = 0; i < cpy.length; i++) {
			cpy[i%4*4+i/4] = state[i / 4][i%4];
		}
		return cpy;
```

#### Method encrypt

|        |                |
| ------ |:--------------:|
| Input  | byte[] |
| Output |     byte[] |

Method used to generate encrypted text.

```java=
		nBlock = 4;
		nKey = key.length/4;
		nRounds = nKey + 6;
		int lenght=0;
		byte[] padding = new byte[1];
		int i;
		lenght = 16 - in.length % 16;				
		padding = new byte[lenght];					
		padding[0] = (byte) 0x80;
		
		for (i = 1; i < lenght; i++) {			
			padding[i] = 0;
		}
		byte[] cpy = new byte[in.length + lenght];		
		byte[] block = new byte[16];							
		w = generateSubkeys(key);
		int count = 0;

		for (i = 0; i < in.length + lenght; i++) {
			if (i > 0 && i % 16 == 0) {
				block = encryptBlock(block);
				System.arraycopy(block, 0, cpy, i - 16, block.length);
			}
			if (i < in.length) {
				block[i % 16] = in[i];
			}
			else {														
				block[i % 16] = padding[count % 16];
				count++;
			}
		}
		if (block.length == 16) {
			block = encryptBlock(block);
			System.arraycopy(block, 0, cpy, i - 16, block.length);
		}
		return cpy;
```

#### Method decrypt

|        |                |
| ------ |:--------------:|
| Input  | byte[] |
| Output |     byte[] |

Method used to decrypt text in order to get plain text.

```java=
		int i;
		byte[] cpy = new byte[in.length];
		byte[] block = new byte[16];
		nBlock = 4;
		nKey = key.length/4;
		nRounds = nKey + 6;
		w = generateSubkeys(key);
		for (i = 0; i < in.length; i++) {
			if (i > 0 && i % 16 == 0) {
				block = decryptBlock(block);
				System.arraycopy(block, 0, cpy, i - 16, block.length);
			}
			if (i < in.length) {
				block[i % 16] = in[i];
			}
		}
		block = decryptBlock(block);
		System.arraycopy(block, 0, cpy, i - 16, block.length);
		cpy = deletePadding(cpy);
		return cpy;
```


### Main.java

This class is where user input is received and the one that calls most of the other classes. It uses graphical interfaces for the selection of files and text capturing.

#### GUI

For a more optimal user experience, a simple graphical interface where an image file can be searched for and text prompts are the way the program receives its parameters.

![](https://i.imgur.com/nEGRQ0k.png)

A cute and simple user interface. Notice the two buttons.

#### Encrypting and embedding

![](https://i.imgur.com/1vIn1n7.png)

Upon clicking in "Embed Text", a file explorer will open. An image file must be selected.

![](https://i.imgur.com/Lw574uE.png)

![](https://i.imgur.com/XQRL5BN.png)

Then, the user will be prompted to enter a 16 character key and a secret message.

![](https://i.imgur.com/19KIbVm.png)

![](https://i.imgur.com/QuvwozG.jpg)

Once the proper parameters are put, a notification will confirm the message encryption and the stego media will show.

#### Extracting and decrypting

![](https://i.imgur.com/8xdivgU.png)

Clicking on "Extract Text" will once again open a file explorer. This time, look for a stego media image (by default, the program saves the stego media in the same directory as the executable).

![](https://i.imgur.com/N3jIVCG.png)

A prompt for an encryption key will appear.

![](https://i.imgur.com/gFptV4m.png)

If the key is correct, the user will be given the message.

## Paradigm

In this project we implemented thread synchronization as an extra security factor. Normally, AES encrypts the whole message with a key given by the user, and returns a ciphered text that is based on the original message. In this situation we noticed that there was a lack of security because if consider the worst case in which a third party can intercept the message when it is traveling through the transmission media or channel, and this third party also gets the encryption key, he would easily decrypt the ciphertext using any AES decrytion tool.

What we are doing with the MultiAES class is that we receive the message given by the user and the key, then we split the message so that we can get an array of strings that contains each of the words of the original message, and then we encrypt word by word using threads so that each thread is in charge of the task of encrypting a single word, and finally this encrypted words are stored in a new string array and finally a new string is formed by appending the multiple ciphered text strings. The output of MultiAES is the message that is stored inside the image using Steganography.

Going back to the worst case situation that was mentioned previously, if a third party intercepts the image created with our program and if somehow he manages to extract the hidden text, he would try to decrypt the message using an AES decryption tool, but the message would have no sense to him, since every word was encrypted separately (using an encrypted key) and combined with the other words, so the only way to get the original plain text is by using our program.

The problem that is solved by thread synchronization is the **race condition** problem that occurs when two or more threads can access shared data and they try to change it at the same time. The thread scheduling algorithm can swap between threads at any time, so we do not know the order in which the threads will attempt to access the shared data. Because of this, the result of the change in data is dependent on the thread scheduling.

The race condition problem could arise when trying to store the encrypted values on the encrypted words array, and the words might be stored in different order, so when decrypted, the message would make no sense to the receiver. This is why a happens-before relationship is made by using synchorization on the encrypt an decrypt methods to prevent race condition problem/memory consistency errors.

AESEncrypt.java

```java=
    @Override
    public void run() {
        try {
            synchronized(this) {
                encryptAES(messageData);            
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```
AESDecrypt.java

```java=
    @Override
    public void run() {
        try {
            synchronized(this) {
                encryptAES(messageData);            
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```

MultiAES.java

```java=
        AESEncrypt encryptedWords[] = new AESEncrypt[messageArray.length];
        Thread threads[] = new Thread[messageArray.length];
        for (int i = 0; i < messageArray.length; i++) {
                encryptedWords[i] = new AESEncrypt(uKey, messageArray[i]);
                threads[i] = new Thread(encryptedWords[i]);
                threads[i].start();
                threads[i].join();
                encryptedMessageArray = addToArray(encryptedMessageArray, encryptedWords[i].getEncryptedValue());
        }
        
        StringBuilder finalMessage = new StringBuilder("");
        for (String string : encryptedMessageArray) {
            finalMessage.append(string);
        }
        return finalMessage.toString();
```
```java=
        AESDecrypt decryptedWords[] = new AESDecrypt[encMessageArray.length];
        Thread threads[] = new Thread[encMessageArray.length];
        for (int i = 0; i < encMessageArray.length; i++) {
                decryptedWords[i] = new AESDecrypt(uKey, encMessageArray[i]);
                threads[i] = new Thread(decryptedWords[i]);
                threads[i].start();
                threads[i].join();
                decryptedMessageArray = addToArray(decryptedMessageArray, decryptedWords[i].getDecryptedStrMessage());
        }
        
        StringBuilder finalDecryptedMessage = new StringBuilder("");

        for (int i = 0; i < decryptedMessageArray.length; i++) {
            if (i == decryptedMessageArray.length - 1) {
                finalDecryptedMessage.append(decryptedMessageArray[i] + ".");
            } else {
                finalDecryptedMessage.append(decryptedMessageArray[i] + " ");
            }
        }
        return finalDecryptedMessage.toString();
```
## Conclusion

As previously demonstrated, both Cryptography and Steganography have their positives and negatives. With that in mind, we aimed to make a system which combined both disciplines in order to make a program that both encrypts and hides confidential information. From our tests and research, we have gotten favorable results, with the obfuscated data being difficult to reveal if anything other than this specific program is used.

One key aspect of the program is its use of threads during the encryption and decryption process. It serves the process in two ways:

1. It lightens the burden the AES algorithm has on a system's resources. One notorious aspect of many Cryptography algorithms is that they are very inefficient, often reaching exponential complexities. While thread synchronization does not reduce the complexity of the program, it does help make the PC use its resources in a more efficient manner by dividing its tasks in their own processes rather than having a single giant loop.
2. It adds another layer of security. Because the program divides the secret phrases into different arrays of words, the information has to be also decrypted word by word. On top of that, the process of decryption becomes even more difficult because of the message's encoding that Java handles and the fact that the encryption key is also encrypted. This means that extracting the message with a program different than this one would be near impossible.

Overall, what's achieved through this program is giving two layers of security to any sensitive information that a user wishes to be hidden through the use of two well known methods of cybersecurity and a reinforcement courtesy of Java's own handling of individual processes through threads.

## References

Richards, K. (2020). cryptography. May 20th, 2021, from TechTarget website: https://searchsecurity.techtarget.com/definition/cryptography#:~:text=Cryptography%20is%20a%20method%20of,"%20stands%20for%20"writing."

Mishra, R & Bhanodiya, P. (2015). A review on steganography and cryptography. May 20, 2

021, from SemanticScholar website: https://www.semanticscholar.org/paper/A-review-on-steganography-and-cryptography-Mishra-Bhanodiya/e77bb2f5cd9c7065e762e1f3eff17fb115be7062#paper-header

Lyer, A. (2019). Image Steganography in Cryptography. May 20, 2021, from GeeksforGeeks website: https://www.geeksforgeeks.org/image-steganography-in-cryptography/

Muzakir, A. (2017). Example of Least Significant Bit. May 20, 2021, from ResearchGate website: https://www.researchgate.net/figure/Example-of-Least-Significant-Bit-LSB-In-figure-4-seen-bits-LSB-at-1-pixel-of-color_fig3_317306868

Oracle (n.d.). Synchronization. May 20, 2021, from Oracle Java website: https://docs.oracle.com/javase/tutorial/essential/concurrency/sync.html.

## Testing

This [Google sheets document](https://docs.google.com/spreadsheets/d/1p5zESUdh6T1ZlnNViGpqwPI2y0jJbkWIXdJ50XMl0do/edit?usp=sharing) contains tests performed by people who downloaded the program from this repository.