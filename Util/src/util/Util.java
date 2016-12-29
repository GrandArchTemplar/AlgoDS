package util;


import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Spliterators.spliteratorUnknownSize;

/**
 * Created by grandarchtemplar on 12/11/16.
 */
public class Util {

    //Zipper for three streams
    @NotNull
    public static <T, U, P, R> Stream<R> triZip(Stream<T> tStream, Stream<U> uStream, Stream<P> pStream,
                                                ThreeFunction<T, U, P, R> triZipper) {
        return StreamSupport.stream(spliteratorUnknownSize(
                new Iterator<R>() {
                    Iterator<T> itT = tStream.iterator();
                    Iterator<U> itU = uStream.iterator();
                    Iterator<P> itP = pStream.iterator();

                    @Override
                    public boolean hasNext() {
                        return itT.hasNext() && itU.hasNext() && itP.hasNext();
                    }

                    @Override
                    public R next() {
                        return triZipper.apply(itT.next(), itU.next(), itP.next());
                    }
                },
                0),
                false
        );
    }

    //Zip with using spliterators and closures
    @NotNull
    public static <T, U, R> Stream<R> zip(Stream<T> tStream, Stream<U> uStream, BiFunction<T, U, R> zipper) {
        return StreamSupport.stream(spliteratorUnknownSize(
                new Iterator<R>() {
                    Iterator<T> itT = tStream.iterator();
                    Iterator<U> itU = uStream.iterator();

                    @Override
                    public boolean hasNext() {
                        return itT.hasNext() && itU.hasNext();
                    }

                    @Override
                    public R next() {
                        return zipper.apply(itT.next(), itU.next());
                    }
                },
                0),
                false
        );
    }

    private static class ClassForReflection<V> {
        public V value;
        public Double val = 0.0;
    }

    //Swapper
    //Type of a must be equal to type of b
    //This implement ignores that final fields can be instanced only one time
    @Deprecated
    public static void swap(Object a, Object b) {
        Class<?> clazz = a.getClass();
        while (clazz != null) {
            Arrays
                    .stream(clazz.getDeclaredFields())
                    .filter(f -> !Modifier.isStatic(f.getModifiers()))
                    .forEach(f -> {
                        f.setAccessible(true);
                        try {
                            Object buffer = f.get(a);
                            f.set(a, f.get(b));
                            f.set(b, buffer);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    });
            clazz = clazz.getSuperclass();
        }
    }


}
