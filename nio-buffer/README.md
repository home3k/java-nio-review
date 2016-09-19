# buffer


命名

[MS][T]Buffer[AM][BO]

MS - memory scheme: Heap or Direct.
T - type: int, short, float, double, char or byte.
AM - access mode: W writable (default), R read-only.
BO - byte ordering: S - non-native, U - native.
B - BigEndian or L - LittleEndian.

classes whose names dont include R, by default are W - writable.

[T]Buffer
Heap[T]Buffer
Heap[T]BufferR
Direct[T]Buffer[S|U]
Direct[T]BufferR[S|U]
ByteBufferAs[T]Buffer[B|L]
ByteBufferAs[T]BufferR[B|L]

比如

ByteBufferAsCharBufferB

```java
    public char get() {
        return Bits.getCharB(bb, ix(nextGetIndex()));
    }

    public char get(int i) {
        return Bits.getCharB(bb, ix(checkIndex(i)));
    }
```

ByteBufferAsCharBufferL

```java
    public char get() {
        return Bits.getCharL(bb, ix(nextGetIndex()));
    }

    public char get(int i) {
        return Bits.getCharL(bb, ix(checkIndex(i)));
    }
```

ByteBufferAsCharBufferRB

```java
    public CharBuffer put(char x) {

        throw new ReadOnlyBufferException();

    }

    public boolean isReadOnly() {
        return true;
    }
```

DirectCharBufferS

```
    public char get() {
        return (Bits.swap(unsafe.getChar(ix(nextGetIndex()))));
    }
```

DirectCharBufferU

```
    public char get() {
        return ((unsafe.getChar(ix(nextGetIndex()))));
    }
```