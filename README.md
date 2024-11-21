# Discount Management System

This documentation outlines the assumptions, implemented cases, and pending enhancements for the discount management system.

## **General Assumptions**
1. A single customer can have only one cart.
2. Each coupon has a unique ID.

**Postman Collection:**  
  A Postman collection is attached to test all the required REST endpoints for this task. This collection includes example requests and responses for each endpoint, making it easy to verify the system's functionality.

## **Implemented Cases**

### 1. Cart-wise Discount
- **Condition:** If the total price of the cart is greater than or equal to the discount threshold, apply the discount.
- **Approach:**
    - Iterate through all the items in the cart and calculate the total price.
    - Check if the total price meets or exceeds the discount threshold.
    - Apply the discount if the condition is met.

---

### 2. Product-wise Discount
- **Condition:** Apply the discount if a product's ID is present in the discount list.
- **Approach:**
    - Iterate through all the items in the cart.
    - Check if the product ID is in the discount list.
    - Apply the discount to the respective products.

---

### 3. BxGy (Buy X, Get Y Free) Discount
- **Approach:**
    1. Create a mapping (`buyProductMap`) of product IDs and quantities for products in the "Buy" list.
    2. Generate a list (`getProductMap`) of products eligible for free or discounted offers.
    3. Iterate through the cart to check if product IDs match the `buyProductMap`.
    4. For each matching product:
        - Verify if the purchased quantity meets or exceeds the offer quantity.
        - Determine how many times the discount can be applied (`offerCount = boughtQuantity / offerQuantity`).

#### **Scenarios:**
1. **`offerCount = 0:`** No discount is applied.
2. **`offerCount > 0 and ≤ allowed repetition:`** Apply the discount `offerCount` times.
3. **`offerCount > allowed repetition:`** Apply the discount up to the maximum allowed repetition.

5. For products in the "Get" list:
    - Prepare a list of product IDs eligible for the discount (`productIdsApplicableForDiscount`).
    - Calculate the number of times the discount can be applied (`noOfTimesOfferApplicable`).

#### **Case-specific Handling:**
- **Case 1:** If the purchased quantity is less than the required offer quantity:
    - `discount += item.getPrice() * buyQuantity`
- **Case 2:** If the purchased quantity exceeds the offer quantity:
    - Calculate `n = buyQuantity / offerQuantity`.
    - **Sub-case 2.1:** If `n ≤ noOfTimesOfferApplicable`:
        - `discount += item.getPrice() * buyQuantity`
        - Reduce the remaining applicable offers: `noOfTimesOfferApplicable -= n`.
    - **Sub-case 2.2:** If `n > noOfTimesOfferApplicable`:
        - Apply all remaining discounts:  
          `discount += item.getPrice() * noOfTimesOfferApplicable`.

#### **Assumptions:**
- There is no overlap between the products in the "Buy" and "Get" lists.

---

## **Unimplemented Cases**

1. **Optimized BxGy Discount Application:**
    - When multiple cart items are eligible for discounts from the "Get" list, the costliest products should be prioritized in descending order.

2. **Multiple Coupon Combinations:**
    - A priority mechanism should be established to determine the sequence of coupon application.
    - Coupons should be applied iteratively on the remaining cart after each application.
3. **Flat Discount Handling in Multi-Offer Scenarios:**
    - When there is a flat discount (instead of a percentage), two options should be considered:
        - **Option 1:** Allow the total amount to be floored to zero if the discount exceeds the remaining purchase amount.
        - **Option 2:** Do not allow the discount to be applied if it exceeds the remaining purchase amount.

4. **Dynamic Coupon Validation:**
    - Validate coupon eligibility in real-time, including scenarios where inventory updates might affect the applicability of "Buy X, Get Y Free" offers.

## **Note**

As I am engaged in my personal office work and due to time constraints, I have not implemented a database for CRUD operations. Instead, required data is cached within the application. This approach allows for efficient data handling without persistent storage.
- Concurrency can also be managed to handle simultaneous execution scenarios, ensuring the consistency and integrity of cached data.


---  
