package com.mypet.web.cmm;
@FunctionalInterface
public interface IConsumer<T> {
	public void accept(T o);
}