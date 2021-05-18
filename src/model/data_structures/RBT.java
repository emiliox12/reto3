package model.data_structures;

public class RBT<K extends Comparable<K>, V extends Comparable<V>> implements ITablaSimbolosOrdenada<K, V> {

	public NodoRBT<K, V> raiz;
	public int size;

	public RBT() {
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return (raiz == null);
	}

	@Override
	public V get(K key) {
		NodoRBT<K, V> actual = raiz;
		while (actual != null) {
			int comparation = actual.getKey().compareTo(key);
			if (comparation == 0) {
				return actual.getValue();
			} else if (comparation > 0) {
				actual = actual.getRight();
			} else {
				actual = actual.getRight();
			}
		}
		return null;
	}

	@Override
	public int height() {
		return raiz.getHeight();
	}

	@Override
	public boolean contains(K key) {
		V value = get(key);
		return (value != null);
	}

	@Override
	public void put(K key, V val) {
		if (raiz != null) {
			raiz = raiz.putRBT(key, val);
		} else {
			raiz = new NodoRBT<K, V>(null, key, val);
		}
	}

	@Override
	public int getHeight(K key) {
		NodoRBT<K, V> actual = raiz;
		int count = 0;
		while (actual != null) {
			int comparation = actual.getKey().compareTo(key);
			if (comparation == 0) {
				return count;
			} else if (comparation > 0) {
				actual = actual.getRight();
			} else {
				actual = actual.getRight();
			}
			count++;
		}
		return -1;
	}

	@Override
	public K min() {
		NodoRBT<K, V> actual = raiz;
		if (actual == null) {
			return null;
		}
		while (actual.getLeft() != null) {
			actual = actual.getLeft();
		}
		return actual.getKey();
	}

	@Override
	public K max() {
		NodoRBT<K, V> actual = raiz;
		if (actual == null) {
			return null;
		}
		while (actual.getRight() != null) {
			actual = actual.getRight();
		}
		return actual.getKey();
	}

	@Override
	public ILista<K> keySet() {
		ILista<K> keys = new ArregloDinamico<>(size);
		ILista<NodoTS<K, V>> stack = new ArregloDinamico<>(size);
		NodoRBT<K, V> actual = raiz;
		if (raiz == null) return keys;
		while (actual != null || stack.size() > 0) {
			while (actual != null) {
				stack.addLast(actual);
				actual = actual.getLeft();
			}
			actual = (NodoRBT<K, V>) stack.removeLast();
			keys.addLast(actual.getKey());
		}
		return keys;
	}

	@Override
	public ILista<V> valueSet() {
		ILista<V> values = new ArregloDinamico<>(size);
		ILista<NodoTS<K, V>> stack = new ArregloDinamico<>(size);
		NodoRBT<K, V> actual = raiz;
		if (raiz == null) return values;
		while (actual != null || stack.size() > 0) {
			while (actual != null) {
				stack.addLast(actual);
				actual = actual.getLeft();
			}
			actual = (NodoRBT<K, V>) stack.removeLast();
			values.addLast(actual.getValue());
		}
		return values;
	}

	@Override
	public ILista<K> keysInRange(K init, K end) {
		ListaEncadenada<K> list = new ListaEncadenada<>();
		NodoRBT<K, V> actual = raiz;
		NodoRBT<K, V> min = new NodoRBT<>(null, init, null);
		NodoRBT<K, V> max = new NodoRBT<>(null, end, null);
		while (actual != null) {
			int comp = actual.compareTo(min);
			if (comp < 0 && actual.right != null) {
				actual = actual.right;
			} else if (comp > 0 && actual.left != null) {
				actual = actual.left;
			} else {
				break;
			}
		}
		while (actual != null) {
			if (actual.compareTo(max) < 0) {
				list.addLast(actual.getKey());
			} else {
				break;
			}
		}
		return list;
	}

	@Override
	public ILista<V> valuesInRange(K init, K end) {
		ListaEncadenada<V> list = new ListaEncadenada<>();
		NodoRBT<K, V> actual = raiz;
		NodoRBT<K, V> min = new NodoRBT<>(null, init, null);
		NodoRBT<K, V> max = new NodoRBT<>(null, end, null);
		while (actual != null) {
			int comp = actual.compareTo(min);
			if (comp < 0 && actual.right != null) {
				actual = actual.right;
			} else if (comp > 0 && actual.left != null) {
				actual = actual.left;
			} else {
				break;
			}
		}
		while (actual != null) {
			if (actual.compareTo(max) < 0) {
				list.addLast(actual.getValue());
			} else {
				break;
			}
		}
		return list;
	}

}
