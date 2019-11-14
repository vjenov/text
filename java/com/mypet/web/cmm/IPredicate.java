package com.mypet.web.cmm;
@FunctionalInterface
public interface IPredicate<T> {
	public boolean test(Object o);
}
