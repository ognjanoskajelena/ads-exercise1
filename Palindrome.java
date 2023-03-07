import java.util.Scanner;

class DLLNode<E> {
  protected E element;
  protected DLLNode<E> pred, succ;

  public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
    this.element = elem;
    this.pred = pred;
    this.succ = succ;
  }

  @Override
  public String toString() {
    return "<-" + element.toString() + "->";
  }
}

class DLL<E> {
  private DLLNode<E> first, last;

  public DLL() {
    this.first = null;
    this.last = null;
  }

  public int length() {
    int ret;
    if (first != null) {
      DLLNode<E> tmp = first;
      ret = 1;
      while (tmp.succ != null) {
        tmp = tmp.succ;
        ret++;
      }
      return ret;
    } else
      return 0;
  }

  public void insertFirst(E o) {
    DLLNode<E> ins = new DLLNode<>(o, null, first);
    if (first == null)
      last = ins;
    else
      first.pred = ins;
    first = ins;
  }

  public void insertLast(E o) {
    if (first == null)
      insertFirst(o);
    else {
      DLLNode<E> ins = new DLLNode<>(o, last, null);
      last.succ = ins;
      last = ins;
    }
  }

  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder();
    if (first != null) {
      DLLNode<E> tmp = first;
      ret.append(tmp).append("<->");
      while (tmp.succ != null) {
        tmp = tmp.succ;
        ret.append(tmp).append("<->");
      }
    } else
      ret = new StringBuilder("Empty list!!!");
    return ret.toString();
  }

  public DLLNode<E> getFirst() {
    return first;
  }

  public DLLNode<E> getLast() {
    return last;
  }
}

public class Palindrome {

  public static boolean isItPalindrome(DLL<Integer> list) {
    DLLNode<Integer> start = list.getFirst();
    DLLNode<Integer> end = list.getLast();

    int n = list.length(), i;
    int middle = (int) Math.ceil(n / 2.0);

    for (i = 1; i <= middle; i++) {
      if (start.element.equals(end.element)) {
        start = start.succ;
        end = end.pred;
      } else
        return false;
    }
    return true;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int n = scanner.nextInt();
    DLL<Integer> list = new DLL<>();

    for (int i = 0; i < n; i++) {
      list.insertLast(scanner.nextInt());
    }

    scanner.close();
    System.out.println(isItPalindrome(list));
  }
}
