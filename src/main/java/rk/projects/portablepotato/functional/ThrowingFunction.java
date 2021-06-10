package rk.projects.portablepotato.functional;

import java.util.function.Function;

/**
 * Represents a function that accepts one argument and produces a result. The function also throws a
 * checked exception.
 *
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 * @param <E> the type of the checked exception thrown by the function
 */
@FunctionalInterface
public interface ThrowingFunction<T, R, E extends Throwable> {

  /**
   * Applies this function to the given argument.
   *
   * @param t the function argument
   * @return the function result
   * @throws E the checked exception.
   */
  R apply(T t) throws E;

  /**
   * Converts a throwing function to a function which throw unchecked exceptions.
   *
   * @param f the throwing function.
   * @param <T> the function argument.
   * @param <R> the function result.
   * @param <E> the checked exception.
   * @return the function result.
   */
  static <T, R, E extends Throwable> Function<T, R> unchecked(ThrowingFunction<T, R, E> f) {
    return t -> {
      try {
        return f.apply(t);
      } catch (Throwable e) {
        throw new RuntimeException(e);
      }
    };
  }
}
