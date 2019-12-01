package com.viewmodel;

import java.util.HashMap;

public class EditorView {

    private HashMap<String, EditableStockView> editable;
    private HashMap<String, String> tickerToStockName;

    public EditorView() {
        editable = new HashMap<String, EditableStockView>();
        tickerToStockName = new HashMap<>();
    }

    public EditorView(HashMap<String, EditableStockView> editable) {
        this.editable = editable;
    }

    public HashMap<String, EditableStockView> getEditable() {
        return editable;
    }

    public void setEditable(HashMap<String, EditableStockView> editable) {
        this.editable = editable;
    }

    public HashMap<String, String> getTickerToStockName() {
        return tickerToStockName;
    }

    public void setTickerToStockName(HashMap<String, String> tickerToStockName) {
        this.tickerToStockName = tickerToStockName;
    }
}
