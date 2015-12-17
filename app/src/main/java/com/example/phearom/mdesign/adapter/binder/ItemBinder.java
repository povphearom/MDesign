package com.example.phearom.mdesign.adapter.binder;

public interface ItemBinder<T>
{
      int getLayoutRes(T model);
      int getBindingVariable(T model);
}