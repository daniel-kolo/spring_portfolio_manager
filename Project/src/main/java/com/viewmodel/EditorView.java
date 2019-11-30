package com.viewmodel;

import java.util.HashMap;

public class EditorView {

    private HashMap<String, StockPartInfoView> editable;

    public HashMap<String, StockPartInfoView> getEditable() {
        return editable;
    }

    public void setEditable(HashMap<String, StockPartInfoView> editable) {
        this.editable = editable;
    }

    public EditorView() {
        editable = new HashMap<String, StockPartInfoView>();
    }

    public EditorView(HashMap<String, StockPartInfoView> editable) {
        this.editable = editable;
    }
}
