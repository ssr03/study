var eventBus = new Vue();
Vue.component("product-tabs", {
  props: {
    reviews: {
      type: Array,
      required: true,
    },
  },
  template: `
    <div>
        <span class="tab"
        :class="{activeTab: selectedTab === tab}"
        v-for="(tab, index) in tabs" 
        :key="index"
        @click="selectedTab=tab"
        >{{tab}}</span>
        <div v-show="selectedTab==='Reviews'">
            <h2>Review</h2>
            <p v-if="!reviews.length">There are no review yet.</p>
            <ul v-else>
                <li v-for="(review, index) in reviews">
                    <p>{{review.name}}</p>
                    <p>Rating: {{review.rating}}</p>
                    <p>{{review.review}}</p>
                </li>
            </ul>
        </div>
        <product-review 
        v-show="selectedTab==='Make a Review'"
        ></product-review>
    </div>
    `,
  data() {
    return {
      tabs: ["Reviews", "Make a Review"],
      selectedTab: "Reviews",
    };
  },
});
Vue.component("product-review", {
  template: `
        <form class="review-form" @submit.prevent="onSubmit">
            
            <p v-if="errors.length">
                <b>please correct the following errors(s):</b>
                <ul>
                    <li v-for="error in errors">{{ error }}</li>
                </ul>
            </p>

            <p>
                <label for="name">Name:</label>
                <input id="name" v-model="name">
            </p>

            <p>
                <label for="review">Review:</label>
                <textarea id="review" v-model="review"></textarea>
            </p>

            <p>
                <label for="rating">rating:</label>
                <select id="rating" v-model.number="rating">
                    <option>5</option>
                    <option>4</option>
                    <option>3</option>
                    <option>2</option>
                    <option>1</option>
                </select>
            </p>

            <p>
                recommendation:
            </p>
            <p>
                <label>Yes
                    <input id="recommendation" type="radio" value="YES" v-model="recommendation">
                </label>
                <label>No
                    <input id="recommendation" type="radio" value="NO" v-model="recommendation">
                </label>
            </p>
            <p>
                <input type="submit" value="submit">
            </p>
        </form>
    `,
  data() {
    return {
      name: null,
      review: null,
      rating: null,
      recommendation: null,
      errors: [],
    };
  },
  methods: {
    onSubmit() {
      if (this.name && this.review && this.rating && this.recommendation) {
        let productReview = {
          name: this.name,
          review: this.review,
          rating: this.rating,
          recommendation: this.recommendation,
        };
        eventBus.$emit("review-submitted", productReview);
        this.name = null;
        this.review = null;
        this.rating = null;
        this.recommendation = null;
      } else {
        this.errors.length = 0;
        if (!this.name) this.errors.push("Name required");
        if (!this.review) this.errors.push("Review required");
        if (!this.rating) this.errors.push("Rating required");
        if (!this.recommendation) this.errors.push("recommendation required");
      }
    },
  },
});
Vue.component("product-detail", {
  props: {
    details: {
      type: Array,
      required: true,
    },
  },
  template: `
    <ul>
        <li v-for="detail in details">{{detail}}</li>
    </ul>
    `,
});
Vue.component("product", {
  props: {
    premium: {
      type: Boolean,
      required: true,
    },
  },
  template: `
    <div class="product">
        <div class="product-image">
            <img v-bind:src="image">
        </div>
        <div class="product-info">
            <h1>{{title}}</h1>
            <p v-if="inStock">In Stock</p>
            <p v-else 
                :class="{outOfStock:!inStock}"
            >Out of Stock</p>
            <p> Shipping: {{ shipping }}</p>
            <product-detail :details="details" ></product-detail>

            <div v-for="(variant, index) in variants" 
            :key="variant.variantIds"
            class="color-box"
            :style="{backgroundColor:variant.variantColor}"
            @mouseover="updateProduct(index)">
            </div>

            <button @click="addToCart" 
            :disabled="!inStock"
            :class="{ disabledButton:!inStock}"
            >Add to Cart</button>
            <button @click="removeCart"
            >Remove from Cart</button>
        </div>
        <product-tabs :reviews="reviews"></product-tabs>
    </div>
    `,
  data() {
    return {
      product: "Socks",
      brand: "Vue Mastery",
      selectedVariant: 0,
      onSale: true,
      details: ["80% cotton", "20% polyester", "Gender-neutral"],
      variants: [
        {
          variantId: 2234,
          variantColor: "green",
          variantImage: "./assets/vmSocks-green-onWhite.jpg",
          variantQuantity: 10,
        },
        {
          variantId: 2235,
          variantColor: "blue",
          variantImage: "./assets/vmSocks-blue-onWhite.jpg",
          variantQuantity: 0,
        },
      ],
      reviews: [],
    };
  },
  methods: {
    addToCart() {
      //this.cart+=1
      this.$emit("add-to-cart", this.variants[this.selectedVariant].variantId);
    },
    removeCart() {
      this.$emit("remove-cart", this.variants[this.selectedVariant].variantId);
    },
    updateProduct(index) {
      this.selectedVariant = index;
    },
    // addReview(productReview){
    //     this.reviews.push(productReview)
    // }
  },
  computed: {
    title() {
      return this.brand + " " + this.product;
    },
    image() {
      return this.variants[this.selectedVariant].variantImage;
    },
    inStock() {
      return this.variants[this.selectedVariant].variantQuantity;
    },
    shipping() {
      if (this.premium) {
        return "Free";
      }
      return "2.99";
    },
  },
  mounted() {
    eventBus.$on("review-submitted", (productReview) => {
      this.reviews.push(productReview);
    });
  },
});

var app = new Vue({
  el: "#app",
  data: {
    premium: true,
    cart: [],
  },
  methods: {
    updateCart(id) {
      this.cart.push(id);
    },
    removeCart(id) {
      for (let i = this.cart.length - 1; i >= 0; i--) {
        if (this.cart[i] === id) {
          this.cart.splice(i, 1);
        }
      }
    },
  },
});
