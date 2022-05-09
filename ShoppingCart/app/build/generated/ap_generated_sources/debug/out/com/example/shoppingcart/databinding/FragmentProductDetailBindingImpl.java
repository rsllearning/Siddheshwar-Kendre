package com.example.shoppingcart.databinding;
import com.example.shoppingcart.R;
import com.example.shoppingcart.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentProductDetailBindingImpl extends FragmentProductDetailBinding implements com.example.shoppingcart.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final android.widget.ScrollView mboundView0;
    @NonNull
    private final android.widget.TextView mboundView4;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentProductDetailBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private FragmentProductDetailBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.Button) bindings[5]
            , (android.widget.TextView) bindings[3]
            , (android.widget.ImageView) bindings[1]
            , (android.widget.TextView) bindings[2]
            );
        this.addToCartButton.setTag(null);
        this.mboundView0 = (android.widget.ScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.priceTextView.setTag(null);
        this.productImageView.setTag(null);
        this.productNameTextView.setTag(null);
        setRootTag(root);
        // listeners
        mCallback1 = new com.example.shoppingcart.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.shopViewModel == variableId) {
            setShopViewModel((com.example.shoppingcart.viewmodels.ShopViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setShopViewModel(@Nullable com.example.shoppingcart.viewmodels.ShopViewModel ShopViewModel) {
        this.mShopViewModel = ShopViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.shopViewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeShopViewModelProduct((androidx.lifecycle.LiveData<com.example.shoppingcart.models.Product>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeShopViewModelProduct(androidx.lifecycle.LiveData<com.example.shoppingcart.models.Product> ShopViewModelProduct, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        com.example.shoppingcart.viewmodels.ShopViewModel shopViewModel = mShopViewModel;
        java.lang.String shopViewModelProductImageUrl = null;
        java.lang.String stringValueOfShopViewModelProductPriceJavaLangString = null;
        java.lang.String shopViewModelProductDescription = null;
        androidx.lifecycle.LiveData<com.example.shoppingcart.models.Product> shopViewModelProduct = null;
        double shopViewModelProductPrice = 0.0;
        java.lang.String stringValueOfShopViewModelProductPrice = null;
        com.example.shoppingcart.models.Product shopViewModelProductGetValue = null;
        java.lang.String shopViewModelProductName = null;

        if ((dirtyFlags & 0x7L) != 0) {



                if (shopViewModel != null) {
                    // read shopViewModel.product
                    shopViewModelProduct = shopViewModel.getProduct();
                }
                updateLiveDataRegistration(0, shopViewModelProduct);


                if (shopViewModelProduct != null) {
                    // read shopViewModel.product.getValue()
                    shopViewModelProductGetValue = shopViewModelProduct.getValue();
                }


                if (shopViewModelProductGetValue != null) {
                    // read shopViewModel.product.getValue().imageUrl
                    shopViewModelProductImageUrl = shopViewModelProductGetValue.getImageUrl();
                    // read shopViewModel.product.getValue().description
                    shopViewModelProductDescription = shopViewModelProductGetValue.getDescription();
                    // read shopViewModel.product.getValue().price
                    shopViewModelProductPrice = shopViewModelProductGetValue.getPrice();
                    // read shopViewModel.product.getValue().name
                    shopViewModelProductName = shopViewModelProductGetValue.getName();
                }


                // read String.valueOf(shopViewModel.product.getValue().price)
                stringValueOfShopViewModelProductPrice = java.lang.String.valueOf(shopViewModelProductPrice);


                // read (String.valueOf(shopViewModel.product.getValue().price)) + ("$")
                stringValueOfShopViewModelProductPriceJavaLangString = (stringValueOfShopViewModelProductPrice) + ("$");
        }
        // batch finished
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.addToCartButton.setOnClickListener(mCallback1);
        }
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, shopViewModelProductDescription);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.priceTextView, stringValueOfShopViewModelProductPriceJavaLangString);
            com.example.shoppingcart.models.Product.loadImage(this.productImageView, shopViewModelProductImageUrl);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.productNameTextView, shopViewModelProductName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // shopViewModel
        com.example.shoppingcart.viewmodels.ShopViewModel shopViewModel = mShopViewModel;
        // shopViewModel.addItemToCart(shopViewModel.product.getValue())
        boolean shopViewModelAddItemToCartShopViewModelProduct = false;
        // shopViewModel != null
        boolean shopViewModelJavaLangObjectNull = false;
        // shopViewModel.product != null
        boolean shopViewModelProductJavaLangObjectNull = false;
        // shopViewModel.product.getValue()
        com.example.shoppingcart.models.Product shopViewModelProductGetValue = null;
        // shopViewModel.product
        androidx.lifecycle.LiveData<com.example.shoppingcart.models.Product> shopViewModelProduct = null;



        shopViewModelJavaLangObjectNull = (shopViewModel) != (null);
        if (shopViewModelJavaLangObjectNull) {




            shopViewModelProduct = shopViewModel.getProduct();

            shopViewModelProductJavaLangObjectNull = (shopViewModelProduct) != (null);
            if (shopViewModelProductJavaLangObjectNull) {


                shopViewModelProductGetValue = shopViewModelProduct.getValue();

                shopViewModelAddItemToCartShopViewModelProduct = shopViewModel.addItemToCart(shopViewModelProductGetValue);
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): shopViewModel.product
        flag 1 (0x2L): shopViewModel
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}