package utils.funcInterfaces;

@FunctionalInterface
public interface FiveFunction<A, B, C, D, E, R> {
    R apply(A arg1, B arg2, C arg3, D arg4, E arg5);
}