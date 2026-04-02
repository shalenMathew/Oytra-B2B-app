# oytra b2b app

### Features

* **Product Details:** Users can view product details which are being fetched from a dummy API stored in GitHub.
  * **Source:** [GitHub Gist](https://gist.github.com/shalenMathew/831599f73356594a960ae1ca60c949b9)

* **User Roles:** Users can enter the app as either a **Retailer** or a **Dealer**.
  * **Pricing Logic:** Dealers see lower prices for products than retailers.

* **Product Components:**
  * Image, Name, Price, and MOQ.
  * Arrow buttons to select the quantity.
  * **Order Button:** Calculates the final price of the particular product based on selected quantity.

 ### Barcode Features

* **Scan Barcode:** Integrated scanner to identify products.
* **Validation Logic:** **Invalid:** Throws "Invalid" if the code contains alphabets or if the last element is an odd number.
  * **Valid:** The barcode is considered "Valid" if the last element is an even number.


### Challenges Faced

*  Setting up the camera can be tricky due to the sensitive permissions and hardware handling required for a smooth user experience.
*  Integrating a 3rd-party library to read barcodes and process results was a challenge, but I used my previous experience to get it working properly.

### Future Improvements

*  I would refine the code structure to make it more scalable and easier to add new features in the future.
*  I would have improved the archhitecture even better  so if new engineers join the team they shall have easier time going through code basee
  
### Download the apk from down below Oytra.apk 👇
https://github.com/shalenMathew/Oytra-B2B-app/releases
