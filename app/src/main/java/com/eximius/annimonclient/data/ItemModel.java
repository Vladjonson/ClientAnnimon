package com.eximius.annimonclient.data;

public class ItemModel implements Comparable<ItemModel> {
    private  boolean isSectionHeader;
    private String itemText;
    private String itemDescription;
	private String itemHeader;

    public ItemModel(String itemDescription, String itemText, String itemHeader) {
        this.itemText = itemText;
        this.itemDescription = itemDescription;
		this.itemHeader = itemHeader;
        this.isSectionHeader = false;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    public String getItemDescription() {
        return itemDescription;
    }

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getItemHeader() {
		return itemHeader;
	}

	public void setItemHeader(String itemHeader) {
		this.itemHeader = itemHeader;
	}

    public boolean isSectionHeader() {
        return isSectionHeader;
    }

	public void setToSectionHeader() {
        isSectionHeader = true;
    }

    @Override
    public int compareTo(ItemModel itemModel) {
        return this.itemHeader.compareTo(itemModel.itemHeader);
    }


}
