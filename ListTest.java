import static org.junit.Assert.*;
import org.junit.Test;
import java.lang.IndexOutOfBoundsException;


public abstract class ListTest {

  private SimpleList<String> e;
  private SimpleList<String> f;

  public ListTest(SimpleList<String> l1, SimpleList<String> l2) {
    e = l1;
    f = l2;
    f.add("Hello");
    f.add("World");
  }

  /**************************************************/
  /* Testing new List is created to specification   */
  /* Doubles as size test. TODO: How to isolate     */
  /* these tests?                                   */
  /**************************************************/
  @Test
  public void testCreate() {
    assertEquals(0, e.size());
  }

  @Test
  public void testCreate2() {
    assertEquals(2, f.size());
  }


  /**********************************/
  /* Testing valid and invalid adds */
  /**********************************/
  @Test(expected = IndexOutOfBoundsException.class)
  public void testAddNegative() {
    e.add(-1, "Hello");
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testAddAfterEnd() {
    e.add(2, "Hello");
  }

  @Test
  public void testAddAtEnd1() {
    e.add(0, "Hello");
    assertEquals(e.get(0), "Hello");
  }

  @Test
  public void testAddAtEnd2() {
    e.add("Hello");
    assertEquals(e.get(0), "Hello");
  }

  @Test
  public void testAddShiftMiddle()
  {
    for(int i = 0; i < 4; i++)
    {
      e.add("hello");
    }

    e.add(2, "nate");
    assertEquals(e.get(2), "nate");
  }

  /**********************************/
  /* Testing valid and invalid gets */
  /**********************************/
  @Test(expected = IndexOutOfBoundsException.class)
  public void testGetFromEmpty() {
    e.get(0);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testGetNegative() {
    f.get(-1);
  }

  @Test
  public void testGetValid() {
    String val = f.get(0);
    assertEquals("Hello", val);
  }

  /*****************************************/
  /* Testing a valid and an invalid remove */
  /*****************************************/

  @Test
  public void testValidRemove() {
    f.remove("hello");
    assertEquals(f.indexOf("hello"), -1);
  }

  @Test
  public void testValidRemove2()
  {
    f.remove(0);
    assertEquals(f.indexOf("World"), 0);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testInvalidRemove() {
    f.remove(2);
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void testNegativeRemove(){
    f.remove(-1);
  }

  /***********************************/
  /* Testing a valid and invalid set */
  /***********************************/

  @Test
  public void testValidSet() {
    f.set(0, "hi");
    assertEquals(f.get(0), "hi");
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testInvalidSet() {
    f.set(6, "hi");
  }

  /***********************************/
  /* Testing isEmpty                 */
  /***********************************/

  @Test
  public void testActuallyEmpty(){
    assertTrue(e.isEmpty());
  }

  @Test
  public void testNotEmpty(){
    assertFalse(f.isEmpty());
  }

  /***********************************/
  /* Testing clear                   */
  /***********************************/

  @Test(expected = IndexOutOfBoundsException.class)
  public void testClear(){
    f.clear();
    f.get(0);
  }

  @Test
  public void testClearTrivial(){
    e.clear();
    assertTrue(e.isEmpty());
  }

  /******************************/
  /* Testing .equals            */
  /******************************/

  @Test
  public void testInvalidClass() {
    e.add("Hello");
    e.add("World");
    assertTrue(e.equals(f));
  }
}
