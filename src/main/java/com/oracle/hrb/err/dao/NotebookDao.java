package com.oracle.hrb.err.dao;

        import com.oracle.hrb.err.bean.Notebook;

        import java.util.List;

public interface NotebookDao{
    List<Notebook> findAll();
    Notebook   findByid();
    Notebook findByName();
}
