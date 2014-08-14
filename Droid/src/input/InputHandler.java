package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
	public boolean _Q, _W, _E, _R, _T, _Z, _U, _I, _O, _P;
	public boolean _A, _S, _D, _F, _G, _H, _J, _K, _L;
	public boolean _Y, _X, _C, _V, _B, _N, _M; 
	public boolean _SHIFT, _ALT, _CTRL, _TAB;
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		//UPPER LETTER ROW
		case KeyEvent.VK_Q:
			_Q = true;
			break;
		case KeyEvent.VK_W:
			_W = true;
			break;
		case KeyEvent.VK_E:
			_E = true;
			break;
		case KeyEvent.VK_R:
			_R = true;
			break;
		case KeyEvent.VK_T:
			_T = true;
			break;
		case KeyEvent.VK_Z:
			_Z = true;
			break;
		case KeyEvent.VK_U:
			_U = true;
			break;
		case KeyEvent.VK_I:
			_I = true;
			break;
		case KeyEvent.VK_O:
			_O = true;
			break;
		case KeyEvent.VK_P:
			_P = true;
			break;
		//MIDDLE LETTER ROW	
		case KeyEvent.VK_A:
			_A = true;
			break;
		case KeyEvent.VK_S:
			_S = true;
			break;
		case KeyEvent.VK_D:
			_D = true;
			break;
		case KeyEvent.VK_F:
			_F = true;
			break;
		case KeyEvent.VK_G:
			_G = true;
			break;
		case KeyEvent.VK_H:
			_H = true;
			break;
		case KeyEvent.VK_J:
			_J = true;
			break;
		case KeyEvent.VK_K:
			_K = true;
			break;
		case KeyEvent.VK_L:
			_L = true;
			break;
		//LOWER LETTER ROW
		case KeyEvent.VK_Y:
			_Y = true;
			break;
		case KeyEvent.VK_X:
			_X = true;
			break;
		case KeyEvent.VK_C:
			_C = true;
			break;
		case KeyEvent.VK_V:
			_V = true;
			break;
		case KeyEvent.VK_B:
			_B = true;
			break;
		case KeyEvent.VK_N:
			_N = true;
			break;
		case KeyEvent.VK_M:
			_M = true;
			break;
		//SPECIAL CHARS
		case KeyEvent.VK_ALT:
			_ALT = true;
			break;
		case KeyEvent.VK_SHIFT:
			_SHIFT = true;
			break;
		case KeyEvent.VK_CONTROL:
			_CTRL = true;
			break;
		case KeyEvent.VK_TAB:
			_TAB = true;
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		//UPPER LETTER ROW
		case KeyEvent.VK_Q:
			_Q = false;
			break;
		case KeyEvent.VK_W:
			_W = false;
			break;
		case KeyEvent.VK_E:
			_E = false;
			break;
		case KeyEvent.VK_R:
			_R = false;
			break;
		case KeyEvent.VK_T:
			_T = false;
			break;
		case KeyEvent.VK_Z:
			_Z = false;
			break;
		case KeyEvent.VK_U:
			_U = false;
			break;
		case KeyEvent.VK_I:
			_I = false;
			break;
		case KeyEvent.VK_O:
			_O = false;
			break;
		case KeyEvent.VK_P:
			_P = false;
			break;
		//MIDDLE LETTER ROW	
		case KeyEvent.VK_A:
			_A = false;
			break;
		case KeyEvent.VK_S:
			_S = false;
			break;
		case KeyEvent.VK_D:
			_D = false;
			break;
		case KeyEvent.VK_F:
			_F = false;
			break;
		case KeyEvent.VK_G:
			_G = false;
			break;
		case KeyEvent.VK_H:
			_H = false;
			break;
		case KeyEvent.VK_J:
			_J = false;
			break;
		case KeyEvent.VK_K:
			_K = false;
			break;
		case KeyEvent.VK_L:
			_L = false;
			break;
		//LOWER LETTER ROW
		case KeyEvent.VK_Y:
			_Y = false;
			break;
		case KeyEvent.VK_X:
			_X = false;
			break;
		case KeyEvent.VK_C:
			_C = false;
			break;
		case KeyEvent.VK_V:
			_V = false;
			break;
		case KeyEvent.VK_B:
			_B = false;
			break;
		case KeyEvent.VK_N:
			_N = false;
			break;
		case KeyEvent.VK_M:
			_M = false;
			break;
		//SPECIAL CHARS
		case KeyEvent.VK_ALT:
			_ALT = false;
			break;
		case KeyEvent.VK_SHIFT:
			_SHIFT = false;
			break;
		case KeyEvent.VK_CONTROL:
			_CTRL = false;
			break;
		case KeyEvent.VK_TAB:
			_TAB = false;
			break;
		default:
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//NOT USED
	}

}
