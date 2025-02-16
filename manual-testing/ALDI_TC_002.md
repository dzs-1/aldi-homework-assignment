## Add two products from the same category and verify them in the shopping list

**Test Case ID**: `ALDI_TC_002`  
**Title**: Add two products from the same category and verify them in the shopping list

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
6. **Navigate** to a same product page under **Products** as in the first step.
7. **Select** a different product to add to the shopping list (e.g., **Apples**).
8. Click on the **"Add to shopping List"** button for the selected product.
9. Wait for a confirmation message popup.
10. **Close** the popup.
11. **Click** on the **"Shopping List"** button in the top-left corner.

---

### Expected Results:
- The product counter increased to **2** in the top-right corner
- The products are in the same category in the shopping list under **Fruit**

---