public class ManAES {

    private static int nBlock, nKey, nRounds;
	private static byte[][] w;

	private static int[] sbox = { 0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F,
			0xC5, 0x30, 0x01, 0x67, 0x2B, 0xFE, 0xD7, 0xAB, 0x76, 0xCA, 0x82,
			0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2, 0xAF, 0x9C,
			0xA4, 0x72, 0xC0, 0xB7, 0xFD, 0x93, 0x26, 0x36, 0x3F, 0xF7, 0xCC,
			0x34, 0xA5, 0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15, 0x04, 0xC7, 0x23,
			0xC3, 0x18, 0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27,
			0xB2, 0x75, 0x09, 0x83, 0x2C, 0x1A, 0x1B, 0x6E, 0x5A, 0xA0, 0x52,
			0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84, 0x53, 0xD1, 0x00, 0xED,
			0x20, 0xFC, 0xB1, 0x5B, 0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C, 0x58,
			0xCF, 0xD0, 0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33, 0x85, 0x45, 0xF9,
			0x02, 0x7F, 0x50, 0x3C, 0x9F, 0xA8, 0x51, 0xA3, 0x40, 0x8F, 0x92,
			0x9D, 0x38, 0xF5, 0xBC, 0xB6, 0xDA, 0x21, 0x10, 0xFF, 0xF3, 0xD2,
			0xCD, 0x0C, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17, 0xC4, 0xA7, 0x7E,
			0x3D, 0x64, 0x5D, 0x19, 0x73, 0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A,
			0x90, 0x88, 0x46, 0xEE, 0xB8, 0x14, 0xDE, 0x5E, 0x0B, 0xDB, 0xE0,
			0x32, 0x3A, 0x0A, 0x49, 0x06, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62,
			0x91, 0x95, 0xE4, 0x79, 0xE7, 0xC8, 0x37, 0x6D, 0x8D, 0xD5, 0x4E,
			0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08, 0xBA, 0x78,
			0x25, 0x2E, 0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xDD, 0x74, 0x1F, 0x4B,
			0xBD, 0x8B, 0x8A, 0x70, 0x3E, 0xB5, 0x66, 0x48, 0x03, 0xF6, 0x0E,
			0x61, 0x35, 0x57, 0xB9, 0x86, 0xC1, 0x1D, 0x9E, 0xE1, 0xF8, 0x98,
			0x11, 0x69, 0xD9, 0x8E, 0x94, 0x9B, 0x1E, 0x87, 0xE9, 0xCE, 0x55,
			0x28, 0xDF, 0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42, 0x68, 0x41,
			0x99, 0x2D, 0x0F, 0xB0, 0x54, 0xBB, 0x16 };

	private static int[] inv_sbox = { 0x52, 0x09, 0x6A, 0xD5, 0x30, 0x36, 0xA5,
			0x38, 0xBF, 0x40, 0xA3, 0x9E, 0x81, 0xF3, 0xD7, 0xFB, 0x7C, 0xE3,
			0x39, 0x82, 0x9B, 0x2F, 0xFF, 0x87, 0x34, 0x8E, 0x43, 0x44, 0xC4,
			0xDE, 0xE9, 0xCB, 0x54, 0x7B, 0x94, 0x32, 0xA6, 0xC2, 0x23, 0x3D,
			0xEE, 0x4C, 0x95, 0x0B, 0x42, 0xFA, 0xC3, 0x4E, 0x08, 0x2E, 0xA1,
			0x66, 0x28, 0xD9, 0x24, 0xB2, 0x76, 0x5B, 0xA2, 0x49, 0x6D, 0x8B,
			0xD1, 0x25, 0x72, 0xF8, 0xF6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xD4,
			0xA4, 0x5C, 0xCC, 0x5D, 0x65, 0xB6, 0x92, 0x6C, 0x70, 0x48, 0x50,
			0xFD, 0xED, 0xB9, 0xDA, 0x5E, 0x15, 0x46, 0x57, 0xA7, 0x8D, 0x9D,
			0x84, 0x90, 0xD8, 0xAB, 0x00, 0x8C, 0xBC, 0xD3, 0x0A, 0xF7, 0xE4,
			0x58, 0x05, 0xB8, 0xB3, 0x45, 0x06, 0xD0, 0x2C, 0x1E, 0x8F, 0xCA,
			0x3F, 0x0F, 0x02, 0xC1, 0xAF, 0xBD, 0x03, 0x01, 0x13, 0x8A, 0x6B,
			0x3A, 0x91, 0x11, 0x41, 0x4F, 0x67, 0xDC, 0xEA, 0x97, 0xF2, 0xCF,
			0xCE, 0xF0, 0xB4, 0xE6, 0x73, 0x96, 0xAC, 0x74, 0x22, 0xE7, 0xAD,
			0x35, 0x85, 0xE2, 0xF9, 0x37, 0xE8, 0x1C, 0x75, 0xDF, 0x6E, 0x47,
			0xF1, 0x1A, 0x71, 0x1D, 0x29, 0xC5, 0x89, 0x6F, 0xB7, 0x62, 0x0E,
			0xAA, 0x18, 0xBE, 0x1B, 0xFC, 0x56, 0x3E, 0x4B, 0xC6, 0xD2, 0x79,
			0x20, 0x9A, 0xDB, 0xC0, 0xFE, 0x78, 0xCD, 0x5A, 0xF4, 0x1F, 0xDD,
			0xA8, 0x33, 0x88, 0x07, 0xC7, 0x31, 0xB1, 0x12, 0x10, 0x59, 0x27,
			0x80, 0xEC, 0x5F, 0x60, 0x51, 0x7F, 0xA9, 0x19, 0xB5, 0x4A, 0x0D,
			0x2D, 0xE5, 0x7A, 0x9F, 0x93, 0xC9, 0x9C, 0xEF, 0xA0, 0xE0, 0x3B,
			0x4D, 0xAE, 0x2A, 0xF5, 0xB0, 0xC8, 0xEB, 0xBB, 0x3C, 0x83, 0x53,
			0x99, 0x61, 0x17, 0x2B, 0x04, 0x7E, 0xBA, 0x77, 0xD6, 0x26, 0xE1,
			0x69, 0x14, 0x63, 0x55, 0x21, 0x0C, 0x7D };

	private static int rcon[] = { 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 
		0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 
		0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 
		0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 
		0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 
		0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 
		0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 
		0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 
		0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 
		0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 
		0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 
		0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 
		0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 
		0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 
		0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 
		0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb };

	private static byte[] manXOR(byte[] a, byte[] b) {
		byte[] out = new byte[a.length];
		for (int i = 0; i < a.length; i++) {
			out[i] = (byte) (a[i] ^ b[i]);
		}
		return out;
	}

	private static byte[][] generateSubkeys(byte[] key) {
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
	}

	private static byte[] subWord(byte[] in) {
		byte[] cpy = new byte[in.length];
		for (int i = 0; i < cpy.length; i++) {
			cpy[i] = (byte) (sbox[in[i] & 0x000000ff] & 0xff);
		}
		return cpy;
	}

	private static byte[] rotateWord(byte[] inBytes) {
		byte[] cpy = new byte[inBytes.length];
		cpy[0] = inBytes[1];
		cpy[1] = inBytes[2];
		cpy[2] = inBytes[3];
		cpy[3] = inBytes[0];
		return cpy;
	}

	private static byte[][] addRoundKey(byte[][] state, byte[][] w, int round) {
		byte[][] cpy = new byte[state.length][state[0].length];
		for (int c = 0; c < nBlock; c++) {
			for (int l = 0; l < 4; l++) {
				cpy[l][c] = (byte) (state[l][c] ^ w[round * nBlock + c][l]);
			}
		}
		return cpy;
	}

	private static byte[][] subBytes(byte[][] state) {
		byte[][] cpy = new byte[state.length][state[0].length];
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < nBlock; col++) {
				cpy[row][col] = (byte) (sbox[(state[row][col] & 0x000000ff)] & 0xff);
			}
		}
		return cpy;
	}
	private static byte[][] invSubBytes(byte[][] state) {
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < nBlock; col++) {
				state[row][col] = (byte)(inv_sbox[(state[row][col] & 0x000000ff)]&0xff);
			}
		}
		return state;
	}

	private static byte[][] shiftRows(byte[][] state) {
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
	}
	
	private static byte[][] invShiftRows(byte[][] state) { 
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
	}

	private static byte[][] invmixColumns(byte[][] s){
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
	}
	
	private static byte[][] mixColumns(byte[][] s){
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
	}

	public static byte byteMul(byte a, byte b) {
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
	}

	public static byte[] encryptBlock(byte[] in) {
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
	}

	public static byte[] decryptBlock(byte[] in) {
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
	}
	
	public static byte[] encrypt(byte[] in,byte[] key){
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
	}
	
	public static byte[] decrypt(byte[] in, byte[] key){
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
	}
	
	private static byte[] deletePadding(byte[] inBytes) {
		int count = 0;
		int i = inBytes.length - 1;
		while (inBytes[i] == 0) {
			count++;
			i--;
		}
		byte[] cpy = new byte[inBytes.length - count - 1];
		System.arraycopy(inBytes, 0, cpy, 0, cpy.length);
		return cpy;
	}
}
