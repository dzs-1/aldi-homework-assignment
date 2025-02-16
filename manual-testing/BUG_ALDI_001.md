## Deleting an item in the shopping list removes all the items

**Bug ID:** `BUG_ALDI_001`

**Title:** Deleting an item in the shopping list removes all the items

**Severity:** `High`

**Priority:** `High`

**Environment:** `PROD-EUC1`

**Browser:** Chrome 133.0.6943.89 (latest)

**OS:** macOS 14 (Sonoma) 

**Resolution:** 1920x1080

**Description:**
When there is more than one item in the shopping list, deleting one of the items deletes the entire shopping list.
---

### Steps to Reproduce:

1. **Navigate** to the [ALDI website](https://www.aldi.us).
2. Add at least two items to the shopping list.
3. **Click** on the shopping list in to top-right corner.
4. **Click** on any delete item button. 

---

### Actual Result:
- Every item is deleted from the shopping list

---

### Expected Result:
- Only the deleted item should disappear from the shopping list

---

### Additional Information:

- Bug is reproducible on Firefox too

---

### Attachments:

- Screen recording of reproduction steps
