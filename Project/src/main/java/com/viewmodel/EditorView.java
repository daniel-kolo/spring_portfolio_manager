package com.viewmodel;

import java.util.HashMap;

public class EditorView {

    private HashMap<String, StockPartInfoView> editable;
    private HashMap<String, String> tickerToStockName;

    public EditorView() {
        editable = new HashMap<String, StockPartInfoView>();
        tickerToStockName = new HashMap<>();
    }

    public HashMap<String, StockPartInfoView> getEditable() {
        return editable;
    }

    public void setEditable(HashMap<String, StockPartInfoView> editable) {
        this.editable = editable;
    }

    public EditorView(HashMap<String, StockPartInfoView> editable) {
        this.editable = editable;
    }

    public HashMap<String, String> getTickerToStockName() {
        return tickerToStockName;
    }

    public void setTickerToStockName(HashMap<String, String> tickerToStockName) {
        this.tickerToStockName = tickerToStockName;
    }
}
