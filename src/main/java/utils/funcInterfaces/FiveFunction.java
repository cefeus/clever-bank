package utils.funcInterfaces;

/**
 * функциональный интерфейс для реализации логики с формирование выписки
 * @param <A> - первый параметр
 * @param <B> - второй параметр
 * @param <C> - третий параметр
 * @param <D> - четвертый параметр
 * @param <E> - пятый параметр
 * @param <R> - возвращаемое значение
 */
@FunctionalInterface
public interface FiveFunction<A, B, C, D, E, R> {
    R apply(A arg1, B arg2, C arg3, D arg4, E arg5);
}
