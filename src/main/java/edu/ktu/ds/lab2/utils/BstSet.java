package edu.ktu.ds.lab2.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Rikiuojamos objektų kolekcijos - aibės realizacija dvejetainiu paieškos
 * medžiu.
 *
 * @param <E> Aibės elemento tipas. Turi tenkinti interfeisą Comparable<E>, arba
 *            per klasės konstruktorių turi būti paduodamas Comparator<E> interfeisą
 *            tenkinantis objektas.
 * @author darius.matulis@ktu.lt
 * @užduotis Peržiūrėkite ir išsiaiškinkite pateiktus metodus.
 */
public class BstSet<E extends Comparable<E>> implements SortedSet<E>, Cloneable {

    // Medžio šaknies mazgas
    protected BstNode<E> root = null;
    // Medžio dydis
    protected int size = 0;
    // Rodyklė į komparatorių
    protected Comparator<? super E> c;

    /**
     * Sukuriamas aibės objektas DP-medžio raktams naudojant Comparable<E>
     */
    public BstSet() {
        this.c = Comparator.naturalOrder();
    }

    /**
     * Sukuriamas aibės objektas DP-medžio raktams naudojant Comparator<E>
     *
     * @param c Komparatorius
     */
    public BstSet(Comparator<? super E> c) {
        this.c = c;
    }

    /**
     * Patikrinama ar aibė tuščia.
     *
     * @return Grąžinama true, jei aibė tuščia.
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * @return Grąžinamas aibėje esančių elementų kiekis.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Išvaloma aibė.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Patikrinama ar aibėje egzistuoja elementas.
     *
     * @param element - Aibės elementas.
     * @return Grąžinama true, jei aibėje egzistuoja elementas.
     */
    @Override
    public boolean contains(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element is null in contains(E element)");
        }

        return get(element) != null;
    }

    /**
     * Patikrinama ar visi abės set elementai egzistuoja aibėje
     *
     * @param set aibė
     * @return
     */
    @Override
    public boolean containsAll(Set<E> set) {
        //TODO
        //throw new UnsupportedOperationException("Studentams reikia realizuoti containsAll(Set<E> set)");

        if (set == null) {
            throw new IllegalArgumentException("Set is null in containsAll(Set<E> set)");
        }

        if (set.isEmpty())
            return false;

        for (E element : set) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Aibė papildoma nauju elementu.
     *
     * @param element - Aibės elementas.
     */
    @Override
    public void add(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element is null in add(E element)");
        }

        root = addRecursive(element, root);
    }

    /**
     * Abės set elementai pridedami į esamą aibę, jeigu abi aibės turi tą patį elementą, jis nėra dedamas.
     *
     * @param set pridedamoji aibė
     */
    @Override
    public void addAll(Set<E> set) {
        //TODO
        //throw new UnsupportedOperationException("Studentams reikia realizuoti addAll(Set<E> set)");

        if (set == null) {
            throw new IllegalArgumentException("Set is null in addAll(Set<E> set)");
        }

        for (E element : set) {
            if (!contains(element)) {
                add(element);
            }
        }
    }

    private BstNode<E> addRecursive(E element, BstNode<E> node) {
        if (node == null) {
            size++;
            return new BstNode<>(element);
        }

        int cmp = c.compare(element, node.element);
        if (cmp < 0) {
            node.left = addRecursive(element, node.left);
        } else if (cmp > 0) {
            node.right = addRecursive(element, node.right);
        }

        return node;
    }

    /**
     * Pašalinamas elementas iš aibės.
     *
     * @param element - Aibės elementas.
     */
    @Override
    public void remove(E element) {
        //TODO
        //throw new UnsupportedOperationException("Studentams reikia realizuoti remove(E element)");
        root = removeRecursive(element, root);
    }

    /**
     * Aibėje lieka tik tie elementai, kurie yra aibėje set.
     *
     * @param set aibė
     */
    @Override
    public void retainAll(Set<E> set) {
        //TODO
        //throw new UnsupportedOperationException("Studentams reikia realizuoti retainAll(Set<E> set)");

        if (set == null) {
            throw new IllegalArgumentException("Set is null in retainAll(Set<E> set)");
        }

        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            E element = iterator.next();
            if (!set.contains(element)) {
                iterator.remove(); // Remove elements not in the provided set
            }
        }
    }

    private BstNode<E> removeRecursive(E element, BstNode<E> node) {
        //TODO
        //throw new UnsupportedOperationException("Studentams reikia realizuoti removeRecursive(E element, BstNode<E> n)");
        if (element == null) {
            throw new IllegalArgumentException("Element is null in removeRecursive(E element, BstNode<E> node)");
        }

        if (node == null) {     // Element not found
            return node;
        }

        int compareResult = c.compare(element, node.element);
        if (compareResult < 0) {                                // In left children
            node.left = removeRecursive(element, node.left);
        } else if (compareResult > 0) {                         // In right children
            node.right = removeRecursive(element, node.right);
        } else {                                                // Current
//            if (node.left == null) {                            //Node with only a right child or no children
//                size--;
//                return node.right;
//            } else if (node.right == null) {                    //Node with only a left child
//                size--;
//                return node.left;
//            } else {                                            //Node with two children
//                E minValue = getMin(node.right).element;
//                node.element = minValue;
//                node.right = removeRecursive(minValue, node.right);
//            }
            if (node.left != null && node.right != null) {
                E minValue = getMin(node.right).element;
                node.element = minValue;
                node.right = removeRecursive(minValue, node.right);
            } else {
                size--;
                return node.left != null ? node.left : node.right;
            }
        }

        return node;
    }

    /**
     * Pašalina maksimalaus rakto elementą paiešką pradedant mazgu node
     *
     * @param node
     * @return
     */
    BstNode<E> removeMax(BstNode<E> node) {
        if (node == null) {
            return null;
        } else if (node.right != null) {
            node.right = removeMax(node.right);
            return node;
        } else {
            return node.left;
        }
    }

    /**
     * Grąžina maksimalaus rakto elementą paiešką pradedant mazgu node
     *
     * @param node
     * @return
     */
    BstNode<E> getMax(BstNode<E> node) {
        return get(node, true);
    }

    /**
     * Grąžina minimalaus rakto elementą paiešką pradedant mazgu node
     *
     * @param node
     * @return
     */
    BstNode<E> getMin(BstNode<E> node) {
        return get(node, false);
    }

    private BstNode<E> get(BstNode<E> node, boolean findMax) {
        BstNode<E> parent = null;
        while (node != null) {
            parent = node;
            node = (findMax) ? node.right : node.left;
        }
        return parent;
    }

    private E get(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element is null in get(E element)");
        }

        BstNode<E> node = root;
        while (node != null) {
            int cmp = c.compare(element, node.element);

            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node.element;
            }
        }

        return null;
    }

    /**
     * Grąžinamas aibės elementų masyvas.
     *
     * @return Grąžinamas aibės elementų masyvas.
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        for (E o : this) {
            array[i++] = o;
        }
        return array;
    }

    public E[] toArray(Class<E> clasz) {
        //Generic array
        E[] array = (E[]) Array.newInstance(clasz, size);
        int i = 0;
        for (E o : this) {
            array[i++] = o;
        }
        return array;
    }

    /**
     * Aibės elementų išvedimas į String eilutę Inorder (Vidine) tvarka. Aibės
     * elementai išvedami surikiuoti didėjimo tvarka pagal raktą.
     *
     * @return elementų eilutė
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (E element : this) {
            sb.append(element.toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * Medžio vaizdavimas simboliais, žiūr.: unicode.org/charts/PDF/U2500.pdf
     * Tai 4 galimi terminaliniai simboliai medžio šakos gale
     */
    private static final String[] term = {"\u2500", "\u2534", "\u252C", "\u253C"};
    private static final String rightEdge = "\u250C";
    private static final String leftEdge = "\u2514";
    private static final String endEdge = "\u25CF";
    private static final String vertical = "\u2502  ";
    private String horizontal;

    /* Papildomas metodas, išvedantis aibės elementus į vieną String eilutę.
     * String eilutė formuojama atliekant elementų postūmį nuo krašto,
     * priklausomai nuo elemento lygio medyje. Galima panaudoti spausdinimui į
     * ekraną ar failą tyrinėjant medžio algoritmų veikimą.
     *
     * @author E. Karčiauskas
     */
    @Override
    public String toVisualizedString(String dataCodeDelimiter) {
        horizontal = term[0] + term[0];
        return root == null ? ">" + horizontal
                : toTreeDraw(root, ">", "", dataCodeDelimiter);
    }

    private String toTreeDraw(BstNode<E> node, String edge, String indent, String dataCodeDelimiter) {
        if (node == null) {
            return "";
        }
        String step = (edge.equals(leftEdge)) ? vertical : "   ";
        StringBuilder sb = new StringBuilder();
        sb.append(toTreeDraw(node.right, rightEdge, indent + step, dataCodeDelimiter));
        int t = (node.right != null) ? 1 : 0;
        t = (node.left != null) ? t + 2 : t;
        sb.append(indent).append(edge).append(horizontal).append(term[t]).append(endEdge).append(
                split(node.element.toString(), dataCodeDelimiter)).append(System.lineSeparator());
        step = (edge.equals(rightEdge)) ? vertical : "   ";
        sb.append(toTreeDraw(node.left, leftEdge, indent + step, dataCodeDelimiter));
        return sb.toString();
    }

    private String split(String s, String dataCodeDelimiter) {
        int k = s.indexOf(dataCodeDelimiter);
        if (k <= 0) {
            return s;
        }
        return s.substring(0, k);
    }

    /**
     * Sukuria ir grąžina aibės kopiją.
     *
     * @return Aibės kopija.
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        BstSet<E> cl = (BstSet<E>) super.clone();
        if (root == null) {
            return cl;
        }
        cl.root = cloneRecursive(root);
        cl.size = this.size;
        return cl;
    }

    private BstNode<E> cloneRecursive(BstNode<E> node) {
        if (node == null) {
            return null;
        }

        BstNode<E> clone = new BstNode<>(node.element);
        clone.left = cloneRecursive(node.left);
        clone.right = cloneRecursive(node.right);
        return clone;
    }

    /**
     * Grąžinamas aibės poaibis iki elemento.
     *
     * @param element - Aibės elementas.
     * @return Grąžinamas aibės poaibis iki elemento.
     */
    @Override
    public Set<E> headSet(E element) {
        //TODO
        //throw new UnsupportedOperationException("Studentams reikia realizuoti headSet()");

        if (element == null) {
            throw new IllegalArgumentException("Element is null in headSet(E element)");
        }

        Set<E> resultSet = new BstSet<>();
        Iterator<E> iterator = new IteratorBst(true);

        while (iterator.hasNext()) {
            E nextElement = iterator.next();

            if (c.compare(nextElement, element) >= 0) {
                break;
            }

            resultSet.add(nextElement);
        }

        return resultSet;
    }

    /**
     * Grąžinamas aibės poaibis nuo elemento element1 iki element2.
     *
     * @param element1 - pradinis aibės poaibio elementas.
     * @param element2 - galinis aibės poaibio elementas.
     * @return Grąžinamas aibės poaibis nuo elemento element1 iki element2.
     */
    @Override
    public Set<E> subSet(E element1, E element2) {
        //TODO
        //throw new UnsupportedOperationException("Studentams reikia realizuoti subSet()");

        if (element1 == null || element2 == null) {
            throw new IllegalArgumentException("Element is null in subSet(E element1, E element2)");
        }

        Set<E> resultSet = new BstSet<>();
        Iterator<E> iterator = new IteratorBst(true);

        while (iterator.hasNext()) {
            E nextElement = iterator.next();

            if (c.compare(nextElement, element1) >= 0) {
                if (c.compare(nextElement, element2) >= 0) {
                    break;
                }
                resultSet.add(nextElement);
            }
        }

        return resultSet;
    }

    /**
     * Grąžinamas aibės poaibis nuo elemento.
     *
     * @param element - Aibės elementas.
     * @return Grąžinamas aibės poaibis nuo elemento.
     */
    @Override
    public Set<E> tailSet(E element) {
        //TODO
        //throw new UnsupportedOperationException("Studentams reikia realizuoti tailSet()");

        if (element == null) {
            throw new IllegalArgumentException("Element is null in tailSet(E element)");
        }

        Set<E> resultSet = new BstSet<>();
        Iterator<E> iterator = new IteratorBst(true);

        while (iterator.hasNext()) {
            E nextElement = iterator.next();

            if (c.compare(nextElement, element) >= 0) {
                resultSet.add(nextElement);
            }
        }

        return resultSet;
    }

    public boolean isBalanced(int k) {
        return isBalanced(k, root);
    }

    private boolean isBalanced(int k, BstNode<E> node) {
        if (node == null) {
            return true;
        }

        int lHeight = height(node.left);
        int rHeight = height(node.right);

        return Math.abs(lHeight - rHeight) <= k && isBalanced(k, node.left) && isBalanced(k, node.right);
    }

    private int height(BstNode<E> node) {
        if (node == null) {
            return 0;
        }

        int lHeight = height(node.left);
        int rHeight = height(node.right);

        return 1 + Math.max(lHeight, rHeight);
    }

    public List<E> insideElementsOfFullTree() {
        ArrayList<E> elements = new ArrayList<>();

        if (root != null && (root.right != null && root.left != null)) {
            outsideRecursive(root.right, true, elements);
            outsideRecursive(root.left, false, elements);
        }

        return elements;
    }

    private void outsideRecursive(BstNode<E> node, boolean ascending, ArrayList<E> elements) {
        if (node == null)
            return;

        if (node.right != null && node.left != null) {
            if (ascending) {
                insideRecursive(node.left, elements);
                outsideRecursive(node.right, true, elements);
            } else {
                insideRecursive(node.right, elements);
                outsideRecursive(node.left, false, elements);
            }
        }
    }

    private void insideRecursive(BstNode<E> node, ArrayList<E> elements) {
        if (node.right != null && node.left != null) {
            elements.add(node.element);
            insideRecursive(node.right, elements);
            insideRecursive(node.left, elements);
        }
    }

//    public ArrayList<E> insideElementsOfFullTree() throws Exception{
//        if (!validateFullTree()) {
//            throw new Exception("Tree is not full!");
//        }
//
//        ArrayList<E> elements = new ArrayList<>();
//        Iterator<E> AIter = new IteratorBst(true);
//        Iterator<E> DIter = new IteratorBst(false);
//        int index = 0;
//
//        while (AIter.hasNext() && DIter.hasNext()) {
//            E elementA = AIter.next();
//            E elementD = DIter.next();
//
//            if (elementA == elementD)
//                break;
//
//            AIter.next();
//            DIter.next();
//
//            if (index == 0 ){
//                AIter.next();
//                DIter.next();
//            } else {
//                for (int i = 0; i < index; i++) {
//                    elements.add(AIter.next());
//                    AIter.next();
//                    elements.add(DIter.next());
//                    DIter.next();
//                }
//            }
//
//            index++;
//        }
//
//        return elements;
//    }
//
//    private boolean validateFullTree() {
//        int value = size;
//        while (value-- != 0) {
//            if (value % 2 != 0) {
//                return false;
//            }
//            value /= 2;
//        }
//        return true;
//    }

    /**
     * Grąžinamas tiesioginis iteratorius.
     *
     * @return Grąžinamas tiesioginis iteratorius.
     */
    @Override
    public Iterator<E> iterator() {
        return new IteratorBst(true);
    }

    /**
     * Grąžinamas atvirkštinis iteratorius.
     *
     * @return Grąžinamas atvirkštinis iteratorius.
     */
    @Override
    public Iterator<E> descendingIterator() {
        return new IteratorBst(false);
    }

    /**
     * Vidinė objektų kolekcijos iteratoriaus klasė. Iteratoriai: didėjantis ir
     * mažėjantis. Kolekcija iteruojama kiekvieną elementą aplankant vieną kartą
     * vidine (angl. inorder) tvarka. Visi aplankyti elementai saugomi steke.
     * Stekas panaudotas iš java.util paketo, bet galima susikurti nuosavą.
     */
    private class IteratorBst implements Iterator<E> {

        private final Stack<BstNode<E>> stack = new Stack<>();
        // Nurodo iteravimo kolekcija kryptį, true - didėjimo tvarka, false - mažėjimo
        private final boolean ascending;
        // Reikalinga remove() metodui.
        private BstNode<E> lastInStack;
        private BstNode<E> last;

        IteratorBst(boolean ascendingOrder) {
            this.ascending = ascendingOrder;
            this.toStack(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.empty();
        }

        @Override
        public E next() {// Jei stekas tuščias
            if (stack.empty()) {
                lastInStack = root;
                last = null;
                return null;
            } else {
                // Grąžinamas paskutinis į steką patalpintas elementas
                BstNode<E> n = stack.pop();
                // Atsimenamas paskutinis grąžintas elementas, o taip pat paskutinis steke esantis elementas.
                // Reikia remove() metodui
                lastInStack = stack.isEmpty() ? root : stack.peek();
                last = n;
                BstNode<E> node = ascending ? n.right : n.left;
                // Dešiniajame n pomedyje ieškoma minimalaus elemento,
                // o visi paieškos kelyje esantys elementai talpinami į steką
                toStack(node);
                return n.element;
            }
        }

        @Override
        public void remove() {
            //TODO
            //throw new UnsupportedOperationException("Studentams reikia realizuoti remove()");

            if (last == null) {
                throw new IllegalStateException("remove() can only be called after a call to next()");
            }

            root = removeRecursive(last.element, root);
            last = null;
        }

        private void toStack(BstNode<E> node) {
            while (node != null) {
                stack.push(node);
                node = ascending ? node.left : node.right;
            }
        }
    }

    /**
     * Vidinė kolekcijos mazgo klasė
     *
     * @param <N> mazgo elemento duomenų tipas
     */
    protected static class BstNode<N> {

        // Elementas
        protected N element;
        // Rodyklė į kairįjį pomedį
        protected BstNode<N> left;
        // Rodyklė į dešinįjį pomedį
        protected BstNode<N> right;

        protected BstNode() {
        }

        protected BstNode(N element) {
            this.element = element;
            this.left = null;
            this.right = null;
        }
    }
}
