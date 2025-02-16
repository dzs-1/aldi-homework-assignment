## Add a single product to the shopping list and verify counter

**Test Case ID**: `ALDI_TC_001`  
**Title**: Add a single product to the shopping list and verify counter

### Preconditions:
- The user is one to the [ALDI website](https://www.aldi.us).
- The shopping list is empty
---

### Test Steps:
1. **Navigate** to a product page under **Products** on the ALDI website (e.g., **Fresh Produce**).
2. **Select** a product to add to the shopping list (e.g., **Bananas**).
3. Click on the **"Add to shopping List"** button for the selected product.
4. Wait for a confirmation message popup.
5. **Close** the popup.

---

### Expected Results:
- The product is **added** to the shopping list successfully.
- The user sees a **confirmation message** ("The product has been successfully added to your shopping list").
- The product counter increased to **1** in the top-right corner

---