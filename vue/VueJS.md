# VUE.js

> https://vuejs.org/v2/guide/
>
> https://kr.vuejs.org/v2/guide/index.html

## 시작하기

### Vue.js가 무엇인가요?

* Vue는 사용자 인터페이스를 만들기 위한 **프로그레시브 프레임워크**
* 다른 단일형 프레임워크와 달리 Vue는 점진적으로 채택할 수 있도록 설계
* 핵심 라이브러리는 뷰 레이어만 초점을 맞추어 다른 라이브러리나 기존 프로젝트와의 통합이 매우 쉬움
* Vue는 **현대적 도구** 및 ** 지원하는 라이브러리**와 함께 사용한다면 정교한 단일 페이지 응용프로그램을 완벽하게 지원 가능

> Vue무료 강의 : https://www.vuemastery.com/courses/intro-to-vue-js/vue-instance/



### 설치하기

* 호환성 정보

  * Vue는 ECMAScript 5 기능을 사용하기 때문에 IE8 이하 버전 지원x
  * 모든 ECMAScript 5 호환 브라우저 지원

* Vue Devtools

  > github: https://github.com/vuejs/vue-devtools#vue-devtools

  * Vue사용할 때, 브라우저에 **Vue Devtools**를 설치 하는 것이 좋습니다
  * Vue 앱을 보다 사용자 친화적인 인터페이스에서 검사하고 디버깅 가능

* 직접 **<script>**에 추가

  * 다운로드 받아 script태그에 추가하기만 하면 됨
  * `Vue`는 전역 변수로 등록됩니다.

* CDN

  * 프로토 타이핑 또는 학습 목적

    ```html
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    ```

  * 프로덕션 환경인 경우 새 버전에서 예상치 못한 오류를 방지하려면 특정 버전의 빌드 파일을 추가하는 것 추천

    ```html
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>
    ```

  * 기본 ES모듈을 사용하는 경우 이를 지원 하는 ES모듈 호환 빌드 파일도 있음

    ```html
    <script type="module">
        import Vue from 'https://cdn.jsdelivr.net/npm/vue@2.6.0/dist/vue.esm.browser.js'
    </script>
    ```

* NPM

  * Vue를 사용하여 대규모 애플리케이션을 구축할 때, NPM를 이용한 설치 권장

  * NPM은 `Webpack`or `Browerify`와 같은 모듈 번들러와 잘 작동

  * Vue는 `싱글 파일 컴포넌트`를 만들기 위한 도구도 제공

    ```shell
    # 최신 안정화 버전
    $ npm install vue
    ```

### 시작하기

* `index.html`파일을 만들고 Vue를 다음과 같이 포함

  ```html
  <!--개발버전, 도움되는 콘솔 경고 포함 -->
  <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
  ```

  또는,

  ```html
  <!--상용버전, 속도와 용량이 최적화됨 -->
  <script src="https://cdn.jsdelivr.net/npm/vue"></script>
  ```

### 선언적 렌더링

* Vue.js의 핵심에는 간단한 템플릿 구문을 사용하여 DOM에서 데이터를 선언적으로 렌더링 할 수 있는 시스템이 있음

  ```html
  <div id="app">
      {{ message }}
  </div>
  ```

  ```javascript
  var app = new Vue({
      el: '#app',
      data:{
          message:'안녕하세요 Vue!'
      }
  })
  ```

  

## Intro to Vue.js

> https://www.vuemastery.com/courses/intro-to-vue-js/attribute-binding

### Attribute Binding

#### v-bind

* Dynamically binds an attribute to an expression

  ```javascript
  v-bind:src="expression"
  ```

  * attribute: v-bind:src

  * 어트리뷰트에 expression을 동적으로 바인딩한다

  * Shorthand: v-bind생략 가능

    ```javascript
    Ex) v-bind:src = "expression"
    :alt="description"
    :href="url"
    :title="toolTip"
    :class="isActive"
    :style="isStyled"
    ```



### Conditional Rendering

```html
<p v-if="inventory > 10">In Stock</p>
<p v-else-if="inventory<=10&&inventory>0">Almost sold out!</p>
<p v-else>Out of Stock</p> 
```

```js
var app= new Vue({
    el:'#app',
    data:{
        inventory:100
    }
})
```



### List Rendering

```html
<ul>
    <li v-for="detail in details">{{detail}}</li>
</ul>
```

* detail: Alias or nickname => {{detail}}로 렌더링

```javascript
var app = new Vue({
    el:'#app',
    data:{
        details:["80% cotton", "20% polyester", "Gender-neutral"]
    }
})
```

* detail은 Collections

```html
<div v-for="variant in variants" :key="variant.variantIds">
    <p>{{variant.variantColor}}</p>
</div>
```

```javascript
var app = new Vue({
    el:"#app"
    data:{
        variants: [
            {
                variantId:2234,
                variantColor:"green"
            },
            { 
                variantId:2235,
                variantColor:"blue"
            }
        ]
    }
})
```



### Event Handling

```html
<div v-for="variant in variants" :key="variant.variantIds">
    <p @mouseover="updateProduct(variant.variantImage)">{{variant.variantColor}}</p>
</div>
<!-- <button v-on:click=" cart+=1 ">Add to Cart</button> -->
<button @click="addToCart">Add to Cart</button>
<div class="cart">
    <p>Cart({{cart}})</p>
</div>
```

```javascript
var app = new Vue({
    el:'#app',
    data:{
        cart:0
    },
    methods:{
         addToCart(){
            this.cart+=1
        }, 
        updateProduct(variantImage){
            this.image = variantImage
        }
    }
})
```



### Class&Style Binding

```html
<div v-for="variant in variants" 
     :key="variant.variantIds"
     class="color-box"
     :style="{backgroundColor:variant.variantColor}"
     @mouseover="updateProduct(variant.variantImage)">
</div>
```

* Style Binding-object

  * ```html
    <span style="color:red; font-size:13px"></span>
    <p :style="[styleObject, styleObject2]">
    </p>
    ```

  * ```javascript
    data:{
        styleObject:{
            color:'red',
            fontSize:'13px'
        },
        styleObject2:{
            margin:'5px',
            padding:'20px'
        }
    }
    ```

* class bindings

  * ```html
     <div v-for="variant in variants" 
          :key="variant.variantIds"
          class="color-box"
          :class="{active:activeClass, 'text-danger':errorClass}"
          :style="{backgroundColor:variant.variantColor}">
    </div>
    ```

    ```javascript
    data:{
        activeClass:true,
        errorClass:false
    }
    ```

  * objects

    ```html
    <div v-for="variant in variants" 
         :key="variant.variantIds"
         class="color-box"
         :class="classObject"
         :style="{backgroundColor:variant.variantColor}">
    </div>
    ```

    ```javascript
    data:{
        classObject:{
            active:true,
            'text-danger':false
        }
    }
    ```

  * array

    ```html
    <div :class="[activeClass, errorClass]">
    ...
    </div>
    ```

    * ['acitve', 'text-danger']

    ```javascript
    data:{
        activeClass:'active',
        errorClass:'text-danger'
    }
    ```

  * conditionals

    ```html
    <div :class="[isActive?activeClass:'']">
     ...
    </div>
    ```

    ```javascipt
    data:{
    	isActive:true,
    	activeClass:'active'
    }
    ```



### Computed Properties

* data에 brand추가

```javascript
data:{
    brand:'Vue Mastery',
    product:'Socks'
},
computed:{
    title(){
        return this.brand+" "+this.product
    }
}    
```

```html
<h1>
    {{ title }}
</h1>
```

* computed property 인 title은 dependency가 변할 때마다 변한다
  
  * 여기서 dependency는 brand와 product
* computed properties에 대해 알아놓아야 할 것은 이 property들이 캐시에 저장된다(cached)
  
  * 그 결과는 dependecy가 변할 때까지 보관된다
* brand와 product는 둘 다 title에 의존성이 있으므로, 만약 brand가 바뀌면, title은 재실행되고 새 값이 캐시에 저장된다.
* 접근할 때마다 재실행하길 원하지 않는 비용이 많이 드는 operation에서는 method보다는 computed property를 쓰는 것이 더 효율적이다

* ex)

  ```javascript
  data:{
      selectedVariant:0,
      variants: [
          {
              variantId:2234,
              variantColor:"green",
              variantImage:"./assets/vmSocks-green-onWhite.jpg"
          },
          { 
              variantId:2235,
              variantColor:"blue",
              variantImage:"./assets/vmSocks-blue-onWhite.jpg"
          }
      ]
  },
  methods:{
      updateProduct(index){
          this.selectedVariant=index
      }
  },
  computed:{
      image(){
          return this.variants[this.selectedVariant].variantImage
      }
  }
  ```

  ```html
  <div class="product-image">
      <img v-bind:src="image">
  </div>
  <div v-for="(variant, index) in variants" 
       :key="variant.variantIds"
       class="color-box"
       @mouseover="updateProduct(index)">
  </div>
  ```



### Components

* component는 재사용 가능한 코드 블럭이다

  * 더욱 모듈화하기 위해, 더욱 유지보수 가능한 코드 베이스를 만들기 위해 사용된다

* Components within components

  * view app은 컴포넌트 안에 컴포넌트를 가진다

* component 등록

  ```javascript
  <!-- Vue.component('[이름]',{[option]})-->
  Vue.component('product',{
      template:`
  		<div>
             	<h1>I'm a component</h1>
  			<h2> Aren't I beautiful?</h2>
  		</div>
  		`,
  		data(){
  			return{
  				...
  			}
  		}
  })
  ```

  * Component template은 하나의 root element를 포함한다
  * Component는 data를 포함
    * 각 컴포넌트에 대한 새로운 data object를 반환
    * 각각의 컴포넌트는 자신의 unique한 데이터를 가지게 된다

* component는 외부의 데이터를 어떻게 접근하는가?

  * component는 자기 자신의 고립된 scope(범위)를 가지기 때문에, 해당 컴포넌트의 외부까지 도달할 수 없다.
  * 그래서 props를 사용

* **props**란?

  * A custom attribute for passing data into our components

    컴포넌트에 데이터를 넘겨주기 위한 커스텀 속성

  * ```javascript
    Vue.component('product',{
    	props: [message],
    	template:`<div>{{message}}</div>`,
    	data(){}
    })
    ```

    ```html
    <product message="hello"></product>
    ```

* 요구사항을 특정화하는 것이 권고된다. 내장된 prop의 validation(유효성)을 사용하자

  * ```javascript
    Vue.component('product',{
        props:{
            message:{
                type:String,
                required:true,
                default:"Hi"
            }
        }
        template:`<div>{{message}}</div>`,
        data(){...}
    })
    ```

* component 형태로 바꾸기

  ```html
  <html>
      <head>
          <title>Product App</title>
          <link rel="stylesheet" type="text/css" href="style.css">
        </head>
      <body>
          <div class="nav-bar"></div>
          <div id="app">
              <product :premium="premium"></product>
          </div>
  
          <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
          <script src="main.js"></script>
      </body>
  </html>
  ```

  ```javascript
  Vue.component('product-detail',{
      props:{
          details:{
              type:Array,
              required:true
          }
      },
      template:`
      <ul>
          <li v-for="detail in details">{{detail}}</li>
      </ul>
      `
  })
  
  Vue.component('product',{
      props:{
          premium:{
              type:Boolean,
              required:true
          }
      },
      template:`
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
              <button v-on:click="minusToCart">Minus to Cart </button>
              <div class="cart">
                  <p>Cart({{cart}})</p>
              </div>
          </div>
      </div>
      `,
      data(){
          return{
              product:'Socks',
              brand:'Vue Mastery',
              selectedVariant:0,
              onSale:true,
              details:["80% cotton", "20% polyester", "Gender-neutral"],
              variants: [
                  {
                      variantId:2234,
                      variantColor:"green",
                      variantImage:"./assets/vmSocks-green-onWhite.jpg",
                      variantQuantity:10
                  },
                  { 
                      variantId:2235,
                      variantColor:"blue",
                      variantImage:"./assets/vmSocks-blue-onWhite.jpg",
                      variantQuantity:0
                  }
              ],
              cart:0
          }
      }, 
      methods:{
          addToCart(){
              this.cart+=1
          },
          updateProduct(index){
              this.selectedVariant=index
          },
          minusToCart(){
              this.cart-=1;
          } 
      },
      computed:{
          title(){
              return this.brand+" "+this.product
          },
          image(){
              return this.variants[this.selectedVariant].variantImage
          },
          inStock(){
              return this.variants[this.selectedVariant].variantQuantity
          },
          shipping(){
              if(this.premium){
                  return "Free"
              }
              return "2.99"
          }  
      }
  })
  
  var app = new Vue({
      el:'#app',
      data:{
          premium:true
      }
  })
  ```

  

### Communicating Events

* cart가 product 컴포넌트의 템플릿 안에 있음->cart를 global position(전역 위치)로 이동->각각의 product는 같은 카트에 추가된다

* ```javascript
  Veu.component('product',{
      ...
      addToCart(){
      	this.$emit('add-to-cart');
  	}
  })
  var app = new Vue({
      el:'#app',
      data:{
          premium:true,
          cart:0
      },
      methods:{
          updateCart(){
              this.cart+=1
          }
      }
  })
  ```

  * 버튼은 이 이벤트를 보낼 파워를 가진다

    * 그러나 받을 수 있는 곳이 없음-> 이벤트에 대해 listen할수 있는 능력을 더해주자

    ```html
    <product :premium="premium" @add-to-cart="updateCart"></product>
    ```

    * `Add to Cart`버튼을 누르면, product 컴포넌트에 있는 이벤트 핸들러에 의해 받아진 신호를 전송한다
      * 이것은 update cart 메서드를  작동시킨다

* add to cart를 누르면 해당 아이템 id 추가하기

  ```html
  <div id="app">
      <div class="cart">
          <p>Cart({{cart.length}})</p>
      </div>
      <product :premium="premium" @add-to-cart="updateCart"></product>
  </div>
  ```

  ```javascript
  Vue.component('product',{
    	props:{
        premium:{
            type:Boolean,
            required:true
        }
    	},
    	..
    	data(){
      	return{
              selectedVariant:0,
              variants:[
                  {
                      variantId:2234,
                      variantColor:"green",
                      variantImage:"./assets/vmSocks-green-onWhite.jpg",
                      variantQuantity:10
                  },
                  { 
                      variantId:2235,
                      variantColor:"blue",
                      variantImage:"./assets/vmSocks-blue-onWhite.jpg",
                      variantQuantity:0
                  }
              ]
          }
  	},
      methods:{
      	addToCart(){
              this.$emit('add-to-cart',this.variants[this.selectedVariant].variantId)
          }
      }
  })
  var app = new Vue({
      el:'#app',
      data:{
          cart:[]
      },
      methods:{
          updateCart(id){
              this.cart.push(id);
          }
      }
  })
  ```



### Forms

* 상품에 대한 리뷰 작성-> user input(사용자 입력)을 모을 수 있는 form작성

* ```javascript
  Vue.component('product-review',{
      template:`
          <input v-model="name">
      `,
      data(){
          return{
              name:null
          }
      }
  })
  ```

  * `v-model="data이름"`: 양방향 data binding

    * 새로운 데이터가 input에 입력될 때마, data가 변하고,

      데이터가 변할 때마다 그 데이터를 쓰는 곳이 어디이던지 간에 업데이트

* ```html
  <product-review></product-review>
  ```

* 리뷰 form

  ```javascript
  Vue.component('product-review',{
      template:`
  	<form class="review-form" @submit.prevent="onSubmit">
  	</form>
  	`
  })
  ```

  * default behavior을 막기 위해 `@submit`에 `.prevent` dot prevent라 불리는 modifier을 붙여 준다

    * 이 form을 submit했을 때, 페이지가 refresh(새로고침)되지 않음

    ```javascript
    Vue.component('product-review',{
        template:`
    	<form class="review-form" @submit.prevent="onSubmit">
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
                    <input type="submit" value="submit">
                </p>
            </form>
    	`,
        data(){
            return{
                name:null,
                review:null,
                rating:null
            }
        },
        methods:{
            onSubmit(){
                let productReview={
                    name:this.name,
                 	review:this.review,
                    rating:this.rating
                }
                this.$emit('review-submitted', productReview)
                
                // 사용자 input 초기화
                this.name=null
                this.review=null
                this.rating=null
            }
        }
    })
    
    Vue.component("product",{
        ..
        template:`
    	<div class="product">
    		<div class="product-image">
    		...
    		</div>
    		<div class="product-info">
    		...
    		</div>
    		<product-review @review-submitted="addReview"></product-review>
    	</div>
    	`,
        data(){
        	...
            reviews:[]
    	},
        methods:{
            ...
            addReview(produtReview){
                this.review.push(productReview)
            }
        }
    })
    ```

* review 보여주기

  ```javscript
  vue.component("product",{
  	template:`
  	...
  	<div>
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
  	<product-review @review-submitted="addReview"></product-review>
  	`,
  	data(){
  		return(){
  			...
  			reviews:[]
  		}
  	}
  })
  ```

* custom form validation

  * native form validation(html5에 내장된 기능)

    ```html
    <textarea id="review" v-model="review" required></textarea>
    ```

  * custome form validation

    * required 속성을 지우고, 데이터를 추가해서 custom form validation을 만듦

    * 에러들을 수집하기 위한 error 배열을 추가한다

      ```javascript
      Vue.component('product-review',{
          template:`
      	...
      	`,
          data(){
              return{
                  name:null,
                  review:null,
                  rating:null,
                  errors:[]
              }
          }
      })
      ```

    * ```javascript
      Vue.component('product-review',{
          template:`
      		<form class="review-form" @submit.prevent="onSubmit">
                  
                  <p v-if="errors.length">
                      <b>please correct the following errors(s):</b>
                      <ul>
                          <li v-for="error in errors">{{ error }}</li>
                      </ul>
                  </p>
      			...
      		</form>
      	`,
          data(){
              return{
                  ...,
                  errors:[]
              }
          },
          methods:{
                onSubmit(){
                  if(this.name&&this.review&&this.rating){
                      let productReview={
                          name: this.name,
                          review: this.review,
                          rating:this.rating
                      }
                      this.$emit('review-submitted', productReview)
                      this.name = null
                      this.review = null
                      this.rating = null
                  }else{
                      this.errors.length=0;
                      if(!this.name)this.errors.push("Name required")
                      if(!this.review)this.errors.push("Review required")
                      if(!this.rating)this.errors.push("Rating required")
                  }
              }     
          }
      })
      ```



### Tabs

* Tab구현하기

  ```javascript
  //tab
  Vue.component('product-tabs',{
      props:{
          reviews:{
              type:Array,
              required:true
          }
      },
      template:`
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
          @review-submitted="addReview"></product-review>
      </div>
      `,
      data(){
          return{
              tabs:['Reviews','Make a Review'],
              selectedTab:'Reviews'
          }
      }
  })
  
  //product
  Vue.component('product',{
      ...
  	template:`
  	<div class="product">
  		...
  		<product-tabs :reviews="reviews"></product-tabs>
  	<div>
  	`,
      ...
  }
  ```

  * addReview 메서드가 없어서 에러 발생

    * `ProductReview`는 `Product`의 자식인`ProductTabs`의 자식(child)이다=>`ProductReview`는 `Product`의 grandchild
  
  * 현재 애플이케이션은 grandchild가 grandparent와 소통(communicate)하도록 설정 x
  
    * 먼저 `Product Review`컴포넌트에서 `review-submitted="addReview"`제거
  
      * 더 이상 `review-submitted 이벤트를 듣지 않고, addReview 메서드가 작동하지 않는다 
  
    * ```javascript
      Vue.component('product-tabs',{
          template:`
          <div>
      	...
      	 <product-review 
              v-show="selectedTab==='Make a Review'"
            ></product-review>
      	</div>
    	`
      })
      ```
  
* 전역 channel 구현-> new Vue instance
  
    ```javascript
  var eventBus = new Vue()
    ```
  
    * application에서 data를 전송하기 위해 `eventBus`를 사용한다 
    * `eventBus`는 review-submitted를 발송하기(emit)위해 사용한다
  
    ```javascript
    Vue.component('product-review',{
        template:`
    		<form class="review-form" @submit.prevent="onSubmit">
    		...
    		</form>
    	`,
        data(){
            name: null,
            review: null,
            rating: null
        },
        methods:{
            onSubmit(){
                if(this.name&&this.review&&this.rating){
                   	  let productReview={
                        name: this.name,
                        review: this.review,
                        rating:this.rating
                    }
                    eventBus.$emit('review-submitted', productReview)
                    ....
                }
            }
        }
    })
    
    Vue.component('product',{
    	...
        template:`
    	<div class="product">
    		<div class="product-image">
    		...
    		</div>
    		<div class="product-info">
    		...
    		</div>
    		<product-tabs :reviews="reviews"></product-tabs>
    	</div>
    	`,
        data(){
            return{
                ...
                reviews:[]
            }
        },
        ...,
        mounted(){
        	eventBus.$on('review-submitted', productReview=>{
              this.reviews.push(productReview)
            })
    	}
    }
    ```
  
    * mounted는 lifecycle hook이다
    * mounted는 컴포넌트가 dom에 마운트되자마자 실행되길 원하는 코드를 두는 곳이다



### Real World vue

* Vue CLI- 프로젝트 셋팅은 Vue CLI로 함
* Vue Router
* Vuex
* Filters
* Mixins
* Lifecycle Hooks
* Custom Directives
* Real data
* API calls w/Axios
* Firebase
* Authentication
* Transitions
* Animations



## Real World Vue.js

> https://www.vuemastery.com/courses/real-world-vue-js/real-world-intro

### Creating our Project(Vue CLI 3)

#### Vue CLI(Command line interface)

* Full system for rapid Vue.js development
  * Dose tedious(지루한) work for us
  * provide features out-of-the-box
* 프로젝트에서 사용할 라이브러리 선택해줌
  * automatically plugs them into the project(자동으로 프로젝트에 플러그인)
* Webpack을 설정해줌
  * javascript files, CSS,  dependencies를 개발을 위해 적절하게 같이 묶고(bundle) 최적화한다
* HTML, CSS& javscript를 마음대로 쓸수 있음
  * single-file.vue 컴포넌트, TypeScript, SCSS, pug, 최신 버전의 ECMAScript등을 사용할 수 있다
* Hot Module Replacement(HMR)을 이용 가능
  * 변화를 저장할때마다 app이 실시간으로 업데이트된다

#### Vue CLI 설치하기

* npm으로 Vue CLI설치

  * node.js설치하기

    ```powershell
    //Node.js 설치 확인
    node -v
    // npm 설치 확인
    npm -v
    ```

  * vue cli 설치

    ```powershell
    npm i -g @vue/cli
    ```

  * `real-world-vue` 프로젝트 만들기 

    ```powershell
    vue create real-world-vue
    ```

    * down arror key를 사용해서 `Manually select features`를 선택한다

      ```powershell
      > Manually select features
      ```

      * vue router, vuex선택(space bar로 선택)
      * Linter formatter은 이미 선택되어 있음

      ->enter

    * ESLint + prettier선택->lint on save

  * 서버 실행

    ```powershell
    cd real-world-vue
    npm run serve
    ```

#### Vue UI

* Command vue UI사용

  ```powershell
  vue ui
  ```

#### Tour the project

* node_modules폴더

  * dependencies needed to build project
  * vue를 build하기 위해 필요한 모든 라이브러리가 저장된 곳

* public 디렉토리

  * put files you don't want run through Webpack
  * Webpack을 통해 처리되길 원하지 않는 image와 같은 파일 두는 곳

* src

  어플리케이션의 특정 코드가 있는 곳

  * assets

    * store assets such as images, fonts
    * 프로젝트에 대한 이미지, 폰트와 같은 assets을 두는 곳

  * components

    * store building blocks of Vue app
    * 컴포넌트를 저장하는 곳 or vue app의 building block을 저장하는 곳

  * views

    * store files for different "pages"
    * app의 페이지 or view 관련 파일 저장

  * App.vue 파일

    * root component
    * all other components are nested within

  * main.js

    * app을 render하고 그것을 Dom에 mount하는 파일

      (renders app and mounts to DOM)

  * router.js

    * vue router을 위한 파일

  * store.js

    * vuex를 위한 store file

  * babel.config.js, package.json

    * npm이 이 프로젝트를 구분하게 도와주고 depnendencies를 다룸

#### How is the app being loaded

* `main.js` 

  * import

    * `Vue`를 import한 것을 볼 수 있음
    * `app.vue`파일도 import 하는 중
    * vue router에 대한 router
    * vuex를 위한 store

  * vue instance 생성

    ```javascript
    new Vue({
        router,
        store,
        render:h=>h(App)
    }).$mount("#app");
    ```

    * new vue instance를 생성하고, router, store을 전달함
    * vue에게 app을 render하라고 명령 `render:h=>h(App)`
    * 이것을 app의 id가 있는 DOM에 mount한다

* app이 load되면 public 디렉토리가 serving up됨으로써 시작된다

  * `index.html`파일이 loading됨
    * `<div id="app"></div>` id가 app인 div가 있음
    * app은 이 위치에 mount됨
  * `app.vue`는 모든 component들이 모여 있는 root 컴포넌트
  * `main.js`파일은 render 메서드 포함
    * app을 가지고 render한 후, app이란 id를 가진 div가 있는 `index.html`에 그것을 mount한다
    * 그러면, app은 DOM에서 나타난다

#### Build process

* Webpack은 모든 파일들을 다같이 묶어둔다(bundling)
* `index.html`파일을 보면, `<!-- built files will be auto injected -->` 코멘트를 볼수 있다
  * built 파일들은 자동으로 주입되어 있음
  * terminal에서, app을 build하기 위해 `npm run build`를 실행 
  * build가 complete되면, dist 디렉토리는 deploy(배포)될 준비가 된다
    * Webpack은 package를 가지고 job script 파일이 주어짐



###  Optimizing your editor

* 최적화된 Vue development 환경을 위한 free Visual Studio Code Editor 셋팅
  * `.vue`파일 syntax 하이라이팅
  * 더 빠른 work flow를 위한 code snippets
  * Auto-formatting
  * Helpful Extension

#### Vetur(extension)

* highlight the syntax in .vue files
* `Vetur` extension 설치

*  **Snippets**
  * Vetur에 따라오는 또 다른 특징은 code snippet이다
  * 코드 snippets은 시간을 절약해 준다
  * **Vetur's `scaffold` shortcut은 `vue`와 교체 될 수 있다**
    * ``<vue``입력시 자동완성 됨
* **Emmet**
  * Vetur에 따라오는 또 다른 패키지
  * code를 build 하기 위한 shortcut으로 사용하는 대중적인 tool
  * ex)
    * h1입력-><h1></h1>
    * div>ul>li입력 해당 태그 자동 완성

#### installing ESLint+prettier

* `eslint` extension

* `prettier` extension설치

* `exlint`와 `prettier` 설정

  * `.eslintrc.js`

    * 이 프로젝트에 대한 ESLint를 설정할 수 있다

  * 이 전용 파일에서 선택되지 않는다면, packeged JSON내에서 ESLint 설정을 찾을 것이다

  * `eslintrc.js`파일내에 `plugin:prettier/recommended`추가

    ```javascript
    // .eslintrc.js
    module.exports={
        ...
        'extends':[
            'plugin:prettier/recommended'
        ]
        ...
    }
    ```

    * .prettierrc.js추가

      ```javascript
      module.exports = {
          singleQuote: true,
          semi: false
      }
      ```

#### User Settings

* 사용자 setting에 설정 추가하기
* file->preferences->settings
  * veture.validation.template set to false

#### Addtional tools

* **copy relative path**
  * Copy Relative Path는 파일 위치를 복사할 수 있도록 하는 extension이다

* open terminal:  ctrl+`
* run task: `npm run serve`
* Snippets Suite
  * VS Code snippets인 full suite of Vue를 다운로드 할 수 있음
  * `sarah.drasner` extension설치

***



## 라우팅

> 공식 라우터: https://router.vuejs.org/

### 단순한 라우팅 시작하기

매우 단순한 라우팅만 필요하고 완전한 기능을 갖춘 라우터 라이브러리를 사용하지 않으려면 다음과 같이 페이지 수준 컴포넌트를 동적으로 렌더링

```javascript
const NotFount = {template:'<p>Page not found</p>'}
const Home = {template:'<p>home page</p>'}
const About = {template:'<p>about page</p>'}

const routes = {
    '/': Home,
    '/about':About
}

new Vue({
    el:'#app',
    data:{
        currentRoute:winodw.location.pathname
    },
    computed:{
        ViewComponent(){
            return routes[this.currentRoute]||NotFound
        }
    },
    render(h){return h(this.ViewComponent)}
})
```



### Vue Router

> https://vueschool.io/courses/vue-router-for-everyone?friend=vuejs

* Vue Router은 Vue.js의 공식 router이다
* app의 다른 페이지들을 돌아다닐 수 있게 해줌
* route transition, dynamic routing, lazy loading 등과 같은 멋진 기능(features)도 커버

#### 1. Introduction to Vue Router

understanding how Vue Router is setup

* page는 종종 view라고 불린다
* `App.vue`
  * `router-link` tags
    * page들을 연결
  * `router-view` tag
    * 주어진 경로에 대한 matched된 컴포넌트를 render하는 함수형 component
  * App.Vue는 single page
    * 우리가 하는 모든 것은 이 single page화면을 변화하는 것이다
    * `router-view`없이는 view를 바꿀 수 없다
    * 기본적으로 `router-view`는 active page(활성화 페이지)의 content를 render한다
      * 사용자가 home page에 방문했을 때, router-view는 Home page의 contents를 렌더할 것이다
      * 사용자가 about page를 방문하면, router-view는 About page의 contents를 렌더한다
    * `router-link`는 router가능한 app 내부의 사용자 내비게이션을 위한 컴포넌트이다
      * `a` tag를 대신 사용하면=> page를 새로고침함
      * SPA(Single Page Application)에서 변경해야 하는 데이터만 가져오기(fetch) 위한 더 작은 요청만 한다
      * SPA는 페이지 변경에 관한 한 더 빠르다
        * 전체 페이지를 reload할 필요가 없기 때문
* `router-link`는 정확히 어떻게 동작하는가?
  * `router-link`는 click event를 가로채(intercept)기 때문에, 브라우저는 이 페이지를 reload를 시도하지 않음
  * 일반적으로, 내부 링크를 위해 `router-link` tag를 사용하고, 외부 링크를 위해 'a' tag를 사용

#### 2. Vue Router Fundamentals

##### Creating Routes

> https://vueschool.io/lessons/vuejs-router-creating-routes 

* `starter files`를 다운로드 한 후 src 아래 assets 폴더를 붙여 넣고, src 아래에 `store.js`도 붙여 넣기

* `view`폴더에 `Brazil.vue`, `Hawaiu.vue`, `Jamaica.vue`, `Panama.vue`등 페이지 생성

  ```vue
  // Brazil.vue
  <template>
    <div>
      <h2>Brazil</h2>
    </div>
  </template>
  ```

* `router`의 `index.js`에 경로와 컴포넌트 속성(property) 추가

  ```javascript
  ...
  import Brazil from "../views/Brazil";
  import Hawaii from "../views/Hawaii";
  import Jamaica from "../views/Jamaica";
  import Panama from "../views/Panama";
  
  //router/index.js
  const routes = [
    {
      path: "/",
      name: "Home",
      component: Home
    },
    {
      path: "/brazil",
      name: "brazil",
      component: Brazil
    },
    {
      path: "/hawaii",
      name: "hawaii",
      component: Hawaii
    },
    {
      path: "/jamaica",
      name: "jamaica",
      component: Jamaica
    },
    {
      path: "/panama",
      name: "panama",
      component: Panama
    }
  ];
  ```

* `App.vue`에 link추가

  ```vue
  <template>
    <div id="app">
      <div id="nav">
        <router-link to="/">Home</router-link>
        <router-link to="/brazil">Brazil</router-link>
        <router-link to="/panama">Panama</router-link>
        <router-link to="/hawaii">hawaii</router-link>
        <router-link to="/jamaica">jamaica</router-link>
      </div>
      <router-view />
    </div>
  </template>
  ```

* destination이 많은 경우, store 파일에서 사용가능한 모든 destinations 로드해서 사용

  * `Home.vue`에 store import 후 데이터 추가

    ```vue
    <script>
        import store from "@/store.js";
        export default{
            name:"home",
            components:{},
            data(){
                return{
                    destinations:store.destinations
                }
            }
        }
    </script>
    ```

  * `Home.vue`에 `v-for`을 가진 div 추가

    ```vue
    <template>
      <div class="home">
        <h1>All Destination</h1>
        <div class="destinations">
          <div v-for="destination in destinations" :key="destination.name">
            <router-link :to="destination.slug">
              <h2>{{ destination.name }}</h2>
            </router-link>
            <figure>
              <router-link :to="destination.name">
                <img :src="require(`@/assets/${destination.image}`)" :alt="destination.name" />
              </router-link>
            </figure>
          </div>
        </div>
      </div>
    </template>
    <style scoped>
    .home {
      max-width: 1400px;
      margin: 0 auto;
    }
    img {
      max-width: 200px;
    }
    .destinations {
      display: flex;
      justify-content: space-between;
    }
    </style>
    ```

##### How to lazy load routes with Vue Router

webpack의 code splitting 기능으로 routes를 lazy load하기

* devtool로 Nework를 보면, `app.js`파일이 load된 걸 볼 수 있다

  * 이 파일은 전체 어플리케이션에 대한 모든 javascript를 로드한다
  * 만약 destination을 클릭하게 되면, javascript가 `app.js`파일에서 로드되었을때와 차이가 없다
  * 이 파일은 대게 javscript bundle로 알려져 있다
  * 홈 페이지를 위해서는 홈페이지에 대한 javascript만 브라질 페이지를 위해서는 브라질 페이지에 필요한 javscript만을 로드하는 등, 실제로 가기 원하는 페이지에 로드 되는 것이 더 좋을것이다

* webpack의 code splitting 기능을 사용하면, bundle을 chunk or bundle이라고 부르는 여러 개의 더 작은 파일로 쪼갤 수 있다
  * route가 방문 될 때에만 각각의 페이지를 쪼개고 요구된 코드를 lazy load한다
  * code splitting은 원하는 것만 load하기 때문에 app을 더 빠르게 만든다
  * 요구되는 bundle들의 파일 사이즈는 하나의 큰 bundle보다 훨씬 작다

* dynamic import

  * `router/index.js`

    ```javascript
    const routes = [
        ...,
        {
            path: "/about",
       		name: "about",
        	component: () =>
            	import(/* webpackChunkName:"about"*/ "./views/about.vue")
        }
    ]
    ```

    * about page는 arrow function내부의 컴포넌트 property에서 import 된다

      ->이것을 `dynamic import`라고 부름

      * routes가 lazy load될 필요가 있다

    * import statement는 오직 arrow function이 호출될 때 실행된다

      * 그러므로, 오직 필요할 때만, 컴포넌트가 lazy loading된다

  * home page제외하고 모든 route를 lazy load하기

    ```javascript
    //routes/index.js
    const routes = [
      {
        path: "/",
        name: "Home",
        component: Home
      },
      {
        path: "/brazil",
        name: "brazil",
        component: () => import("../views/Brazil")
      },
      {
        path: "/hawaii",
        name: "hawaii",
        component: () => import("../views/Hawaii")
      },
      {
        path: "/jamaica",
        name: "jamaica",
        component: () => import("../views/Jamaica")
      },
      {
        path: "/panama",
        name: "panama",
        component: () => import("../views/Panama")
      }
    ];
    ```

    * `component: Hawaii`를 `component: ()=>import("../views/Hawaii")`로 변경

  * chunk에 magic comment추가

    * magic comment로 `/`와 `*`를 가지는 것은 중요

    ```javascript
    const routes = [
      {
        path: "/",
        name: "Home",
        component: Home
      },
      {
        path: "/brazil",
        name: "brazil",
        component: () => import(/*webpackChunkName: "barzil"*/ "../views/Brazil")
      },
      {
        path: "/hawaii",
        name: "hawaii",
        component: () => import(/*webpackChunkName: "hawaii"*/ "../views/Hawaii")
      },
      {
        path: "/jamaica",
        name: "jamaica",
        component: () => import(/*webpackChunkName: "jamaica"*/ "../views/Jamaica")
      },
      {
        path: "/panama",
        name: "panama",
        component: () => import(/*webpackChunkName: "panama"*/ "../views/Panama")
      }
    ];
    ```

    * webpackChunkName추가
      *  network tab에서 JS를 선택하면 magic comment의 이름의 js 파일이 로드된 것을 볼 수 있음

##### Vue Router Active Class

* Navigation 컴포넌트 만들기

  * 컴포넌트 이름은 존재하거나 미래의 HTML element와 충돌하지 않기 위해 muti-worded여야 한다

  * TheNavigation.vue

    ```vue
    <template>
      <nav id="nav">
        <router-link to="/">Home</router-link>
        <router-link to="/brazil">brazil</router-link>
        <router-link to="/panama">Panama</router-link>
        <router-link to="/hawaii">Hawaii</router-link>
        <router-link to="/jamaica">Jamaica</router-link>
      </nav>
    </template>
    
    <style scoped>
    #nav {
      display: flex;
      align-items: center;
    }
    </style>
    ```

  * App.vue에 붙이기

    ```vue
    //App.vue
    <template>
      <div id="app">
        <TheNavigation />
        <router-view />
      </div>
    </template>
    <script>
        import TheNavigation from "@/components/TheNavigation";
        export default{
            components:{
                TheNavigation
            }
        }
    </script>
    <style>
    ...
    #nav a.vue-school-active-class {
      color: #ab26ab;
    }
    </style>
    ```

  * `router/index.js`

    ```javascript
    ...
    const router = new VueRouter({
      linkExactActiveClass: "vue-school-active-class",//링크색 설정함
      mode: "history",
      base: process.env.BASE_URL,
      routes
    });
    ```

  * navigation에 관련된 style은 App.vue에서 TheNavigation.vue로 이동

    * `#nav`,`#nav a`, `#nav a.vue-schoole-active-class`를 `TheNavigation.vue`로 이동

##### Vue Router Named Routes and Params

* devtools에서 `<Root>`를 클릭한다면, $route property를 가지는 것을 볼 수 있음

  * path와 이름 뿐 아니라 params, query, meta에도 접근할 수 있음

* component에서 이 data를 어떻게 접근하는가

  * Vue.use(router)와 같이 router plugin을 사용하기 때문에, 실제로 모든 컴포넌트에 있는 this.$route에 있는 router instance를 접근 할 수 있다
  * main Vue instance에서 전역으로 이용 가능하기 때문

* 한 페이지에 각각의 destination에 대한 detail을 가지기 위해 route params 사용하기

  * Destination Detail page만들기

    * `views`폴더에 `DestinationDetails.vue` 파일 만들기

      ```vue
      //DestinationDetails.vue
      <template>
        <h2>The destination is: {{ this.$route.params.id }}</h2>
      </template>
      ```

  * 새로운 route를 router에 추가

    ```javascript
    //router/index.js
    const routes = [
        ...
        {
        path: "/details",
        name: "DestinationDetails",
        component: () =>
          import(
            /*webpackChunkName: "DestinationDetails"*/ "../views/DestinationDetails"
          )
      }
    ]
    ```

  * Home page에서 위의 새로운 페이지를 연결할 수 있는 destination 이름과 이미지 링크를 만들기

    * 각각의 path에 직접 연결하는 대신에 named routes사용

      * `Named routes`는 path를 알지 못해도 route에 직접 연결하도록 한다

        ->path가 바뀌어도 나중에 refactor할 필요 x

        ->모든 것이 `router.js`에서 통제

      ```vue
      //home.vue
      <template>
        <div class="home">
          <h1>All Destination</h1>
          <div class="destinations">
            <div v-for="destination in destinations" :key="destination.name">
              <router-link
                :to="{ name: 'DestinationDetails', params: { id: destination.id } }"
              >
                <h2>{{ destination.name }}</h2>
              </router-link>
              <figure>
                <router-link
                  :to="{ name: 'DestinationDetails', params: { id: destination.id } }"
                >
                  <img
                    :src="require(`@/assets/${destination.image}`)"
                    :alt="destination.name"
                  />
                </router-link>
              </figure>
            </div>
          </div>
        </div>
      </template
      ```

      * `<router-link :to="{name: 'DestinationDetails'}"></router>`

    * `named routes`를 사용하기 위해서, name property를 가진 object를 추가한다

      * 링크걸기 원하는 페이지 이름

    * Destination에 id 추가

      * destination을 클릭하면 알맞은 id가 보일 것이다

      * 이를 위해 `params`을 사용할 필요가 있다

      * Home page에 id param을 전달하기 위해 router link에게 전달해야 함

        ```vue
        //Home.vue
        ...
         <router-link
                  :to="{ name: 'DestinationDetails', params: { id: destination.id } }">
             <h2>{{ destination.name }}</h2>
        </router-link>
        ...
        ```

        * `router-link :to="{name: 'DestinationDetails', params: { id: destination.id }}"></router>`

      * `DestinationDetail`page에 paragraph tag를 추가

        ```vue
        <template>
          <h2>The destination is: {{ this.$route.params.id }}</h2>
        </template>
        ```

##### Vue Router Dynamic Routes

`DestinationDetail`에 destination의 디테일을 프린트하기

* `router.js`에서 id param을detail path에 추가하기

  ```javascript
  //router/index.js
  const routes = [
      ...
      {
          path: "/details/:id",
          name: "DestinationDetails",
          component: () =>
            import(
              /*webpackChunkName: "DestinationDetails"*/ "../views/DestinationDetails"
            )
        }
       ...
  ]
  ```

* `DestinationDetail`페이지에서 param에서 value를 가져오기

  * `destinationid`라 불리는 새로운 데이터 속성 만들기

  * `this.$route.params.id`값 가져오기

    ```vue
    //DestinationDetail.vue
    <template>
    	<h2>The destination is: {{ this.$route.params.id }}</h2>
    </template>
    <script>
    export default {
        data(){
            return{
                destinationId:this.$route.params.id
            }
        }
    }
    </script>
    ```

  * template에서 destination이름, description, 사진 추가

    ```vue
    //DestinationDetails.vue
    <template>
      <section class="destination">
        <h1>{{ destination.name }}</h1>
        <div class="destination-details">
          <img
            :src="require(`@/assets/${destination.image}`)"
            :alt="destination.name"
          />
        </div>
      </section>
    </template>
    ```

  * destination을 호출하는 함수를 가진 computed property추가

    * computed property: data와 같은 방법으로 사용되지만, dependency(의존성)에 기반하여 cache된 커스텀한 로직을 가짐

    * `DestinationDetails.vue`

      ```vue
      ...
      <script>
          import store from "@/store.js";
      	export default{
              data(){
                  ....
              },
              computed:{
                  destination(){
                      return store.destinations.find(
                      	destination => destination.id === this.destinationId
                      );
                  }
              }
          }
      </script>
      ```

      * `find()`메소드는 제공된 testing 함수를 만족하는 array의 첫 번째 element의 값을 반환

##### How to rerender components when vue router params changes

static page와 route를 제거할 필요가 있음

* 모든 destination에 loop를 걸기 위해 v-for 추가하고, 각각의 destination에 `destination.id  `의 값을 가지고 있는 id param을 가지고 있는`destinationDetails`페이지로 갈 수 있는 router-link tag출력

* `TheNavigation.vue`에서 static link지우기

  ```vue
  //TheNavigation.vue
  <template>
    <nav id="nav">
      <p class="logo">The Vue School Travel App</p>
      <ul class="nav-links">
        <li class="links">
          <router-link to="/">Home</router-link>
        </li>
        <li
          v-for="destination in destinations"
          :key="destination.name"
          class="links"
        >
          <router-link
            :to="{
              name: 'DestinationDetails',
              params: { id: destination.id }
            }"
          >
            {{ destination.name }}
          </router-link>
        </li>
      </ul>
    </nav>
  </template>
  <script>
  import store from "@/store";
  export default {
    data() {
      return {
        destinationId: this.$route.params.id,
        destinations: store.destinations
      };
    }
  };
  </script>
  ```

* navbar에서 home, brazil이외의 것은 클릭해도 작동x

  * Vue Router은 어떠한 변화도 감지하지 못하였기 때문

  * 만약 같은 컴포넌트가 사용된다면, component를 새로운 데이터로 reload할 필요가 있다

  * `router-view`에 `$route.path`값을 key로 추가해서 reload할 수 있다

    ```vue
    //App.vue
    <template>
      <div id="app">
        <TheNavigation />
        <router-view :key="$route.path" />
      </div>
    </template>
    ```

    * key를 가지면, path의 변화는 새로운 데이터로 컴포넌트 relaod를 유발

* static page지우기

  * `route.js`에서 view folder로부터 4개의 destination제거

    ```javascript
    ...
    const routes = [
      {
        path: "/",
        name: "Home",
        component: Home
      },
      {
        path: "/details/:id",
        name: "DestinationDetails",
        component: () =>
          import(
            /*webpackChunkName: "DestinationDetails"*/ "../views/DestinationDetails"
          )
      }
    ];
    ...
    ```

##### How to pass Vue Router params as props to components

URL에서 id대신에 destination의 이름이 보이길 원하는 경우

* `router.js` 파일에서 param을 id에서 slug로 변경

  ```javascript
  const routes = [
      ...
       {
      path: "/details/:slug",
      name: "DestinationDetails",
      component: () =>
        import(
          /*webpackChunkName: "DestinationDetails"*/ "../views/DestinationDetails"
        )
    }
  ]
  ```

* destination detail page에서 destinationId를 slug로 바꿔야 한다

  * `DestinationDetails.vue`

    ```vue
    ...
    <script>
    import store from "@/store.js";
    export default {
      data() {
        return {
          slug: this.$route.params.slug
        };
      }
      ...
    </script>
    ```

* computed property에서, `destination.id`를 `destination.slug`로 바꿔야 함

  params도 또한 id대신에 slug로 변경

  * `DestinationDetail.vue`

    ```vue
    <script>
    export default {
      ....,
      computed: {
        destination() {
          return store.destinations.find(
            destination => destination.slug === this.slug
          );
        }
      }
    };
    </script>
    ```

*  id 대신에 slug param을 가지도록 `router-links`를 변경

  * `Home.vue`

    ```vue
    <template>
    <div class="home">
        <h1>All Destination</h1>
        <div class="destinations">
          <div v-for="destination in destinations" :key="destination.name">
            <router-link
              :to="{
                name: 'DestinationDetails',
                params: { slug: destination.slug }
              }"
            >
         	<h2>{{ destination.name }}</h2>
        	</router-link>
        ...  
    </template>
    ```

  * `TheNavigation.vue`

    ```vue
    <template>
    ...
    	<ul class="nav-links">
          <li class="links">
            <router-link to="/">Home</router-link>
          </li>
          <li
            v-for="destination in destinations"
            :key="destination.name"
            class="links"
          >
            <router-link
              :to="{
                name: 'DestinationDetails',
                params: { slug: destination.slug }
              }"
            >
              {{ destination.name }}
            </router-link>
          </li>
        </ul>
    ...
    </template>
        
    ```

  * 더 이상 사용하지 않으므로, 데이터에 있는 `destinatonId`제거

    ```vue
    <script>
    import store from "@/store";
    export default {
      data() {
        return {
          destinations: store.destinations
        };
      }
    };
    </script>
    ```

* component에 `$route`를 사용하는 것은 component의 flexibility(유연성)를 제한하는 route에 tight coupling(타이트한 연결)을 만든다

  -> 특정한 URL에서만 사용될 수 있다

* router로부터 이 component를 분리시키기 위해서, `props`를 사용한다

* `$route` 대신에 `props`를 사용해서 app refector

  * `router.js`파일에 props property추가

    ```javascript
    ...
    const routes = [
      {
        path: "/",
        name: "Home",
        component: Home,
        props: true
      },
      {
        path: "/details/:slug",
        name: "DestinationDetails",
        props: true,
        component: () =>
          import(
            /*webpackChunkName: "DestinationDetails"*/ "../views/DestinationDetails"
          )
      },
      ...
    ]
    ```

  * Destination Detail페이지에 props추가

    ```vue
    // DestinationDetails.vue
    ...
    <script>
    import store from "@/store.js";
    export default {
      data() {
        return {
        };
      },
      props: {
        slug: {
          type: String,
          required: true
        }
      },
      ...
    </script>
    ```

    * props property를 추가하고, param인 slug property를 내부에 추가한다
    * slug에는 String type을 추가하고 required를 true로 만든다
      * page가 동작하기 위해서는 prop가 요구되기 때문에  
    * slug에 대한 data property는 더 이상 필요 없으므로 제거한다
      * props로부터 직접적으로 갑을 가져온다

##### Vue Router Nested Routes

* Destination Details 컴포넌트에 experiences 클래스를 가진 새로운 section추가

* `DestinationDetails.vue`

  ```vue
  <template>
    <div>
      <section class="destination">
        <h1>{{ destination.name }}</h1>
        <div class="destination-details">
          <img
            :src="require(`@/assets/${destination.image}`)"
            :alt="destination.name"
          />
          <p>
            {{ destination.description }}
          </p>
        </div>
      </section>
      <section class="experiences">
        <h2>Top experiences in {{ destination.name }}</h2>
        <div class="cards">
          <div
            v-for="experience in experiences"
            :key="experience.slug"
            class="card"
          >
            <img
              :src="require(`@/assets/${experience.image}`)"
              alt="experience.name"
            />
            <span class="card_text">{{ expereince.name }}</span>
          </div>
        </div>
      </section>
    </div>
  </template>
  <script>
  ```

  * property or 메서드 experiences는 instance에서 정의되지 않고 render하는 동안 참조된다

* destinations내부에 experiences가 있으므로 아래와 같이 바꿀 수 있다

  ```vue
  ...
  <div
       v-for="experience in destination.experiences"
       :key="experience.slug"
       class="card"
  >
  ...
  ```

* experiences에 대한 detail추가

  * `ExperienceDetails.vue`

    ```vue
    <template>
      <section>
        <h1>Experiences</h1>
        <h2>{{ experience.name }}</h2>
        <div class="experience-details">
          <img
            :src="require(`@/assets/${experience.image}`)"
            :alt="experience.name"
          />
          <p>{{ experience.description }}</p>
        </div>
      </section>
    </template>
    
    <script>
    import store from "@/store.js";
    export default {
      props: {
        slug: {
          type: String,
          required: true
        },
        experienceSlug: {
          type: String,
          required: true
        }
      },
      computed: {
        destination() {
          return store.destinations.find(
            destination => destination.slug === this.slug
          );
        },
        experience() {
          return this.destination.experiences.find(
            experience => experience.slug === this.experienceSlug
          );
        }
      }
    };
    </script>
    ```

* Destination Details page에 router-link 태그 추가

  * `DestinationDetails.vue`

    ```vue
    ...
    <section class="experiences">
        <h2>Top experiences in {{ destination.name }}</h2>
        <div class="cards">
            <div
                 v-for="experience in destination.experiences"
                 :key="experience.slug"
                 class="card"
                 >
                <router-link
                             :to="{
                                  name: 'ExperienceDetails',
                                  params: { experienceSlug: experience.slug }
                                  }"
                             >
                    <img
                         :src="require(`@/assets/${experience.image}`)"
                         alt="experience.name"
                         />
                    <span class="card_text">{{ experience.name }}</span>
                </router-link>
            </div>
        </div>
    </section>
    ...
    ```

* 새로운 페이지를 클릭하는게 아니라 experiences리스트 아래에서 같은 페이지에 experieces를 보여주기 원함

  * 이것은 일반적인 pattern으로, 이러한 routes를 `nested routes`라 부름

* Destination Details page에서도 위와 같이 하기 위해서, router-view tag를 추가한다

  * experience detail view를 로드할 수 있다

  * `$route.path`의 키를 추가하면 navigation event가 발생할 때마다 router-view컴포넌트를 대체하도록 한다

    ```vue
    <template>
    <div>
        <section class="destination">
          ...
        </section>
        <section class="experiences">
        	<h2>Top experiences in {{ destination.name }}</h2>
          	<div class="cards">
                ...
        	</div>
            <router-view :key="$route.path"/>
        </section>
    </div>
    </template>
    ```

    * router view에 키를 추가하지 않으면, 업데이트 되지 않음

* `router.js`에 experiece Details를 만들기

  * `router/index.js`

    ```javascript
    ...
    const routes = [
        ...
        {
        path: "/details/:slug",
        name: "DestinationDetails",
        props: true,
        component: () =>
          import(
            /*webpackChunkName: "DestinationDetails"*/ "../views/DestinationDetails"
          ),
        children: [
          {
            path: ":experienceSlug",
            name: "ExperienceDetails",
            props: true,
            component: () =>
              import(
                /*webpackChunkName: "ExperienceDetails"*/ "../views/ExperienceDetails"
              )
          }
        ]
      }
    ]
    ```

    * childeren property를 만들어서 그 내부에 experienceDetails에 대한 route를 둔다

##### Creating a Go-Back button

> https://vueschool.io/lessons/creating-a-go-back-button

`go back`버튼 추가하기

* 이전에 방문했던 페이지를 쉽게 돌아다닐 수 있다

* components에 `GoBack.vue`추가

  ```vue
  <template>
    <span class="go-back">
      <button @click="goBack">go back</button>
    </span>
  </template>
  
  <script>
  export default {
    methods: {
      goBack() {
        return this.$router.go(-1);
      }
    }
  };
  </script>
  ```

  * 클릭 이벤트를 가진 v-on directive 추가
    * v-on directive는 DOM event를 듣는다
    * 이벤트가 시행되면, JavaScript구동
    * v-on은 shorthand(약칭)는 `@` 기호
  * `goBack`함수를 만들어야 함
    * method를 사용
      * method: Vue instance와 연관된 함수
    * method는 `methods` property내부에 정의된다
      * methods 내부에 `goBack`함수가 만들어짐
    * 이 함수는 router에게 한 route로 돌아가라고 지시하는 `$router.go`를 사용
      * 이 메서드는 매개변수로 단일 integer를 가진다
        * 이 매겨변수는 앞으로 몇 step을 가는지 지시
        * history stack에서 뒤로 몇 step을 가는지 지시
      * 뒤로 한번만 가기를 원하므로 value값을 -1로 전달

* `DestinationDetails.vue`

  ```vue
  <template>
    <div>
      <GoBack />
     ...
      </div>
  </template>
  <script>
  import GoBack from "@/components/GoBack";
  export default {
    components: {
      GoBack
    },
    ...
  }
  </script>
  ```

  * `DestinationDetails.vue`에 `GoBack` 버튼 import 
    * component를 우선 import
    * component의 이름을 `components` property에 추가
    * template에 이 component추가

##### How to create route transitions with Vue Router

* Vue는 transition wrapper component를 제공

  * 이 컴포넌트는 element나 component에 대해 들어가거나 나가는 transition을 추가하도록 한다

* 한 element가 transition component로 둘러싸여있다면(wrapped), Vue는 자동적으로 목표 element가 CSS transition을 가지고 있는지, animation이 적용되었는지,  만약 그렇게 했다면, 적절한 타이밍에 CSS transition class가 추가되거나 제거 되었는지 확인한다 

* `App.vue`에 transition 태그를 추가한다

  * 이 컴포넌트를 import할 필요는 없다
  * template에 직접 사용할 수 있다
  * css class slide의 transition을 적용할 name slide를 사용할 수 있다
  * `out-in`모드를 추가한다
    * 현재 element transition이 처음에 나오고, 완료되면 새로운 element transition이 들어온다

  ```vue
  //App.vue
  <template>
    <div id="app">
      <TheNavigation />
      <transition name="slide" mode="out-in">
        <router-view :key="$route.path" />
      </transition>
    </div>
  </template>
  <style>
  ...
      
  .slide-enter-active,
  .slide-leave-active {
    transition: opacity 1s, transform 1s;
  }
  .slide-enter,
  .slide-leave-to {
    opacity: 0;
    transform: translateX(-30%);
  }
  </style>
  ```

##### How to create a 404 Not Found route and page with Vue Router

* 존재하지 않는 route(경로)로 가게 되면, navigation만 있는 page를 보게 된다

  -> not found page를 만들어 보자

* not found component를 추가한다

  * `views`폴더에 `NotFound.vue`추가

    ```vue
    <template>
      <div>
        <h1>Not Found</h1>
        <p>
          Oops we couldn't find that page. Try going
          <router-link :to="{ name: 'Home' }">home</router-link>
        </p>
      </div>
    </template>
    ```

    * home page로 갈 수 있는 router-link포함

  * `router.js`에 route추가

    ```javascript
    const routes=[
        ...
        {
            path: "*",
            name: "notFound",
            component: () =>
              import(
                /* webpackChunkName: "NotFound" */
                "../views/NotFound"
              )
          }
    ]
    ```

* `localhost:8080/destination/maxico`와 같이 변경하는 경우에는 `Not Found Page`가 뜨지 않음

  * 이러한 경우에 URL은 route 패턴에 매치되고, router는 route를 제공한다
  * `store.js`파일에 Mexico를 가지고 있지 않고 router는 그 파일에 무엇이 있는지 인식할 수 없음
  * destination route를 로드하기 전에, destination이 실제로 존재하는지 확인하는 방법이 필요
  * `navigation guard`를 사용해서 이러한 것을 할 수 있음

* Vue router은 몇 개의 다양한 `navigation gaurds`를 제공

  * navigation(이동)이 발생하기 전 후에 코드가 실행되도록 하는 Vue의 lifecycle hook으로써 navigation gaurds를 생각할 수 있다
  * `beforeEnter`을 사용할 것이다. 이전에, 무엇인가 발생하길 원하는 route에 들어간다
  * `beforeEnter`함수는 인자 세 개를 받는다
    * to:  목표 Route Object
    * from: 떠나게 될 현재 route
    * next: 이 함수는 hook을 해결하기 위해 호출돼야만 하는 것, action은 next에 제공된 인자에 달려있다

* route에 `beforeEnter guard`추가

  * `router/index.js`

    ```javascript
    ...
    import store from "@/store";
    ...
    const routes=[
        ...
       {
        path: "/details/:slug",
        name: "DestinationDetails",
        props: true,
        component: () =>
          import(
            /*webpackChunkName: "DestinationDetails"*/ "../views/DestinationDetails"
          ),
        children: [
          {
            path: ":experienceSlug",
            name: "ExperienceDetails",
            props: true,
            component: () =>
              import(
                /*webpackChunkName: "ExperienceDetails"*/ "../views/ExperienceDetails"
              )
          }
        ],
        beforeEnter: (to, from, next) => {
          const exists = store.destinations.find(
            destination => destination.slug === to.params.slug
          );
          
          if (exists) {
            next();
          } else {
            next({ name: "notFound" });
          }
        }
      }, 
      ...
    ]
    ```

    * 일어나기 원하는 것은 그 route에 들어가기 전이어야 한다.
    * params에서 부터 location이 store에서 존재하는 location과 동일한지 확인해야 한다.
      * 만약 동일하다면, route가 진행되도록 하고, destination을 보여 준다
      * 만약 동일하지 않다면, notFound page로 가야 한다
    * beforeEnter 함수에서 find()메서드 사용

* console창에 warning 뜸: missing param for named route 'notFound:Expected 0 to be defined'

  * 기본적으로 현재 뷰를 asterisk(별표가 있는) 경로를 가진 named route와 바꿀 수 없다
  * 현재 뷰를 notFound 경로와 매치될 명확한 path와 바꾸어야 한다
  * 이것을 위해, alias(별칭)을 사용
  * 404경로에 대한 asterisk(별표의)의 별칭은 사용자가 404페이지를 방문했을 때, URL은 404가 되지만,  사용자가 asterisk(별표)를 방문했을 때와 같이 매치되는 것을 의미한다

* 경로를 404로 변경하고, asterisk(별표)의 별칭을 추가하자

  * `router/index.js`

    ```javascript
    ...
    const routes = [
        ...
        {
        path: "/404",
        alias: "*",
        name: "notFound",
        component: () =>
          import(
            /* webpackChunkName: "NotFound" */
            "../views/NotFound"
          )
      }
    ]
    ```

    * `path:"*"`->`path:"/404"`로 변경
    * `alias:"*"`추가

##### How to control the scroll behavior of Vue Router

> https://vueschool.io/lessons/how-to-control-the-scroll-behavior-of-vue-router

<p>client-side의 라우팅을 사용할때, 맨 위로 스크롤하길 원하고, <br>
    새로운 route로 이동할때, history객체에 스크롤링 위치를 저장하기 원한다<br>
    vue-router가 이러한 것을 할 수 있도록 하고, route navigation에서 스크롤 동작을 커스터마이즈 하도록 한다.</p>

* experiences를 깔끔하게 하고, 몇 개의 스크롤 동작을 추가

  -> experiences의 리스트가 페이지의 맨 위에 머무를 수 있도록

* router에  `scrollBehavior`을 추가

  * `router/index.js`

    ```javascript
    ...
    const router = new VueRouter({
      linkExactActiveClass: "vue-school-active-class",
      mode: "history",
      base: process.env.BASE_URL,
      scrollBehavior(to, from, savedPosition) {
        if (savedPosition) {
          return savedPosition;
        } else {
          const position = {};
          if (to.hash) {
            position.selector = to.hash;
            return false;
          }
        }
      },
      routes
    });
    ```

    * `scrollBehavior(to, from, savdePosition)`
      * 세 번째 인자`savedPosition`은 브라우저에서 이전/이후 버튼으로 이동할때 native-like동작을 야기한다
    * `scroll to anchor` 동작을 시뮬레이트 하기 원한다면,  `to.hash`사용
      * hash가 있다면, selector를 return함으로써 anchor를 스크롤 한다
      * 리턴된 위치가 false이거나 빈 객체이면, 현재 스크롤 위치를 얻는다

* `DestinationDetails` component에 `router-link` 태그에 hash를 추가

  * `DestinatonDetails.vue`

    ```vue
    <template>
    ....
        <section class="experiences" id="experience">
          <h2>Top experiences in {{ destination.name }}</h2>
          <div class="cards">
            <div v-for="experience in destination.experiences" :key="experience.slug" class="card">
              <router-link
                :to="{
                  name: 'ExperienceDetails',
                  params: { experienceSlug: experience.slug },
                  hash: '#experience'
                }"
              >
                <img :src="require(`@/assets/${experience.image}`)" alt="experience.name" />
                <span class="card_text">{{ experience.name }}</span>
              </router-link>
            </div>
          </div>
          <router-view :key="$route.path" />
        </section>	
    ...
    </template>
    ```

    * hash에는 experience의 id를 준다

    * anchor tag가 되길 원하는 곳이나 page가 스크롤 되기 원하는 곳에 그 id를 추가한다

      `<div class="cards" id="experience">`

* 그러나, 가장 아래에서 스크롤하면, 원하지 않는 효과가 나온다(맨 아랫단에서 experience를 클릭하면 맨 위로 scroll됨)

  * hash가 있는지 확인하고 position을 return해야 한다

    ```javascript
    //router/index.js
    ...
    const router = new VueRouter({
      linkExactActiveClass: "vue-school-active-class",
      mode: "history",
      base: process.env.BASE_URL,
      scrollBehavior(to, from, savedPosition) {
        if (savedPosition) {
          return savedPosition;
        } else {
          const position = {};
          if (to.hash) {
            position.selector = to.hash;
            if (document.querySelector(to.hash)) {
              return position;
            }
            return false;
          }
        }
      },
      routes
    });
    ```

  * transition이 여전히 문제를 일으킴->transition에 mode out-in추가하기

    ```vue
    <!--App.vue-->
    <template>
      <div id="app">
        <TheNavigation />
        <transition name="fade" mode="out-in">
          <router-view :key="$route.path" />
        </transition>
      </div>
    </template>
    ...
    ```

* navigation을 맨 위에 고정시킴

  * experience에 있으면서, 스크롤없이 다양한 destination으로 변경할 수 있다

  * sticky 위치를 사용해서 css를 변형해야 한다

  * `TheNavigation.vue`

    ```vue
    ...
    <style>
    #nav {
      display: flex;
      align-items: center;
      position: sticky;
      top: 0;
      background-color: white;
      border-bottom: 1px solid grey;
      z-index: 1;
    }
    ...
    </style>
    ```

##### How to configure an authentication middleware(route guard) with Vue Router

login form으로 사용자에게 인증을 요구함으로써 새로운 페이지를 만들고 보호할 것이다

* 사용자와 패스워드가 데이터베이스 안에 있는 것과 일치하는지 확인하고 사용자가 로그인하도록 허용

* route를 보호하기 위해서, 전역 navigation guards를 사용한다

* navigation guards를 route에 들어가기 전과 후에 구동 될 수 있는 전통적인 미드웨어 hooks로 여길 수 있다

* 전역 guard를 정의하기 위해, router instance에 접근할 필요가 있다

  * `router/index.js`

    ```javascript
    ...
    router.beforeEach((to, from, next) => {});
    export default router;
    ```

    * export statement이전에 `router.beforeEach`를 이용해 guard를 만든다
    * `BeforeEach`는 `to`,`from`,`next` 인자를 받는 callback 함수를 받아들인다

* 오직 본인만 접근할 수 있는 사용자 페이지 만들기

  * `views`폴더에 `User.vue` 만들기

    ```vue
    <template>
      <h1>user</h1>
    </template>
    ```

  * `router/index.js`에서 사용자 페이지에 대한 route 등록

    ```javascript
    const routes = [
        ..
      {
        path: "/user",
        name: "user",
        component: () => import(/* webpackChunkName: "User" */ "../views.User.vue")
      },
      ...
    ]
    ```

* route guard에게 route가 보호돼야 한다고 어떻게 말하는가?

  * Vue router는 route를 정의할 때 포함할 수 있는 `meta fields`라 부르는 무엇 인가를 지원한다

  * User route에 `meta attribute` 추가

    ```javascript
    // router/index.js
    const routes = [
      ...
      {
        path: "/user",
        name: "user",
        component: () => import(/* webpackChunkName: "User" */ "../views/User.vue"),
        meta: { requiresAuth: true }
      },
      ...
    ]
    ```

    * meta property는 object, 이 object에 필요한 것이 무엇이든 넣을 수 있음
    * meta fields가 있으면 필요한 고급 route 로직을 만들 수 있다
      * ex) permission에 기반한 행동을 막기 위해서 or authentication을 요구하려고

  * route guard에서, meta field를 접근할 수 있다

    ```javascript
    //router/index.js
    ...
    router.beforeEach((to, from, next) => {
      if (to.meta.requiresAuth) {
        // need to login
      } else {
        next();
      }
    });
    ```

    * 가고자 하는 route가 `requiredAuth`의 meta가 있는지 확인
    * 있다면, 그 페이지에 방문하기 전에 사용자에게 로그인을 요구

* 로그인을 하기 위해 로그인 페이지가 필요

  * `views`폴더에 `Login.vue`생성

    ```vue
    <template>
      <div class="login">
        <h1>Login</h1>
        <div class="form">
          <label for="username">Username</label>
          <input v-model="username" type="text" name="username" class="input" />
          <label for="password">Password</label>
          <input  v-model="password" type="text" name="password" class="input" />
          <button class="btn">Login</button>
        </div>
      </div>
    </template>
    ```

  * data에 username과 password를 null로 셋팅

    ```vue
    ...
    <script>
    export default {
      data() {
        return {
          username: null,
          password: null
        };
      }
    };
    </script>
    ```

  * 버튼에 클릭했을 때 로그인 메서드를 호출하는 click event추가

    ```vue
    <template>
      <div class="login">
        <h1>Login</h1>
        <div class="form">
          ...
          <button @click="login" class="btn">Login</button>
        </div>
      </div>
    </template>
    
    <script>
    export default {
      data() {
        ...
      },
      methods: {
        login() {
          //Authenticate against API
        }
      }
    };
    </script>
    ```

    * 실제 인증을 수행하지는 않을 것이다
    * 실제 애플리케이션에서 `login()`은 API에 대한 사용자를 인증하는 곳이다

* 사용자가 성공적으로 인증되었음을 가정하고 `store.js`에 username을 저장한다

  ```javascript
  //store.js
  export default {
    user: null,
      ...
  }
  ```

* `Login.vue`에서 store을 사용하기 위해 import 한다

  ```vue
  ...
  <script>
  import store from "@/store";
  ...
  </script>
  ```

* login 매세드 안에, 로그인 폼으로 부터 user의 값을 업데이트 할 수 있다

  ```vue
  <script>
  import store from "@/store";
  export default {
    data() {
      return {
        username: null,
        password: null
      };
    },
    methods: {
      login() {
        store.user = this.username;
        this.$router.push("/user");
      }
    }
  };
  </script>
  ```

* `this.$router.push("/")`를 사용하여 그 사용자를 우리가 원하는 곳으로 redirect할 수 있다

* `router/index.js`에서 로그인 페이지에 대한 route추가

  ```javascript
  const routes = [
    ...
    {
      path: "/login",
      name: "login",
      component: () =>
        import(/* webpackChunkName: "Login" */ "../views/Login.vue")
    },
    ...
  ]
  ```

* `router/index.js`에서 사용자가 인증되었는지 아닌지 체크

  ```javascript
  ...
  router.beforeEach((to, from, next) => {
    if (to.meta.requiresAuth) {
      if (!store.user) {
        next({
          name: "/login"
        });
      }else {
        next();
      }
    } else {
      next();
    }
  });
  ```

* store에 user가 없다면, 로그인 페이지로 redirect

  * 컴포넌트의 name을 사용자가 redirect되길 원하는 곳으로 하고, `next`를 호출하면 로그인 페이지 redirect가능

  * 사용자가 인증되었다면, `next()`만 호출

* routes설정에 있는 각각의 route 객체는 `route record`라고 불린다

  * `route records`는 중첩되어(nested) 있을 것이다. 따라서 route가 매치되면 하나 이상의 route record가 매치될 수 있다.

  * 예) 만약 experiences route에 meta field를  추가한다면, experiences가 destinations의 child이기 때문에 experiences route는 destination route를 매치할 것이다

  * 매치된 routes가 특정한 meta를 가지고 있는지 확인하는 가장 좋은 방법은 `route.matched` property를 사용하는 것이다

    ```javascript
    // router/index.js
    router.beforeEach((to, from, next) => {
      //if (to.meta.requiresAuth) {
      if (to.matched.some(record => record.meta.requiresAuth)) {
        if (!store.user) {
          next({
            name: "login"
          });
        } else {
          next();
        }
      } else {
        next();
      }
    });
    ```

    * `some` javascript 매세드 사용
      * some: array에 최소한 하나의 요소가 test를 통과하는지 아닌지 테스트
    * record가 `meta.requiresAuth`를 가진 하나와 매치된다면, 로그인을 한다

* Navigation component(`TheNavigation.vue`)에 route추가하기

  ```vue
  <template>
  	<nav id="nav">
          ...
      	<ul class="nav-links">
          ....
          	<li class="links">
          		<router-link to="/user">Dashboard</router-link>
        		</li>
      	</ul>
      </nav>
  </template>
  ```

* user page에 로그아웃 버튼 만들기

  ```vue
  <!-- User.vue -->
  <template>
    <div>
      <h1>Welcome {{ user }}</h1>
      <button @click="logOut" class="btn">Log out</button>
    </div>
  </template>
  
  <script>
  import store from "@/store";
  export default {
    data() {
      return { user: store.user };
    },
    methods: {
      logOut() {
        store.user = null;
        this.$router.push("/");
      }
    }
  };
  </script>
  ```

  * logOut method를 가진 클릭 이벤트 추가
    * store에 있는 user 값이 null로 리셋되게 함
    * 로그인 메서드에서와 같이 `router.push`사용해서 사용자를 home page로 리다이렉트

##### Vue Router Query Parameters

쿼리 parameters가 동작하는 방법

`Query parameters`는 URL에서 정의된 변수(variables)

* `http://localhost:8080/?destination=brazil&experience=salvador`url을 브라우저에 추가하면, 

  Vue dev tools에서 보면, `$route.object`에  query key object가 있고, 그 내부에 destination,   experience 키를 가지고 그 값으로 brazil과 salvador를 가짐

* query parameter는 search or filtering에서 등에 사용

* `views`폴더에 보호된(protected) 페이지`invoice.vue` 만들기

  ```vue
  <template>
    <h1>my invoices</h1>
  </template>
  ```

* `router/index.js`에 route 추가

  ```javascript
  const routes = [
      ...
      {
      	path: "/invoices",
      	name: "invoices",
      	component: () =>
        		import(/* webpackChunkName: "Invoices" */ "../views/invoices.vue"),
      	meta: { requiresAuth: true }
      },
      ...
  ]
  ```

* User page(`User.vue`)에 invoices 링크 만들기

  ```vue
  <template>
    <div>
      <h1>Welcome {{ user }}</h1>
      <p>
        <router-link :to="{ name: 'invoices' }">Invoices</router-link>
      </p>
      <button @click="logOut" class="btn">Log out</button>
    </div>
  </template>
  ```

* user가 성공적으로 로그인한 후에 올바른 url로 redirect하길 원한다

  * state(상태)에 redirect URL을 저장할 수 있고, 또는 query params를 사용할 수 있다

  * URL에 redirect parameter(매게변수)를 추가한다면, 로그인 컴포넌트에서 사용자를 리다이텍팅 했을 때, 대신 경로로 사용할 수 있다

  * `Login.vue`에서 redirect path를 추가

    ```vue
    <script>
    import store from "@/store";
    export default {
      data() {
        return { user: store.user };
      },
      methods: {
        logOut() {
          store.user = null;
          this.$router.push("/");
          const redirectPath = this.$route.query.redirect|| "/";
          this.$router.push(redirectPath);
        }
      }
    };
    </script>
    ```

    * query parameter를 사용할 수 없는 경우일 때이다. 그리므로 그것에 대한 서포트를 추가하자
    * redirectPath가 존재하지 않으면, redirectPath를 `this.$route.query.redirect`나 home route로 설정한다

  * `router/index.js`에서 query param을 스스로 설정할 수 있다

    ```javascript
    ...
    router.beforeEach((to, from, next) => {
      //if (to.meta.requiresAuth) {
      if (to.matched.some(record => record.meta.requiresAuth)) {
        if (!store.user) {
          next({
            name: "login",
            query: { redirect: to.fullPath }
          });
        } else {
          next();
        }
      } else {
        next();
      }
    });
    ...
    ```

    * `next()`를 호출할 때 query property를 설정함으로써 사용자가 어디로 가길 원하는지 알 수 있음

    * redirect에 `to.fullPath`를 설정한다

    * 현재 URL에 가기를 원하는 올바를 루트로 redirect param을 가진다

    * http://localhost:8080/invocies를 입력하면, 로그인 창이 뜨고 URL은 `http://localhost:8080/login?redirect=%2Finvoices`이 된다

    * 일단 로그인하면, invoices route가 올바른 page로 이동된다 

      

## Vue.js Components Fundamentals

> https://vueschool.io/courses/vuejs-components-fundamentals

### 1. Components Basics

#### Introduction to Components

* component는 name과 같이 재사용 가능한 Vue instance이다

* 컴포넌트의 목적

  * 애플리케이션의 몇 기능을 캡슐화 하는 것

* 컴포넌트는 전체 페이지나 navigation bar와 같은 큰 부분부터 버튼이나 특별하게 포맷되어 있는 텍스트와 같은 작은 부분까지 될 수 있다

* 한 element를 마운트하고 있는 vue 인스턴스를 가진다

* `Vue.component`으로 컴포넌트 만들기

  * 이 함수는 컴포넌트의 첫 번째 인자에 이름을 받고, 두 번째 인자에 option을 가지고 있는 객체를 받는다

  * `app.js`

    ```javascript
    Vue.component("click-counter", {
      template: "<button @click='click'>{{count}}</button>",
      data() {
        return {
          count: 0,
        };
      },
      methods: {
        click() {
          this.count++;
        },
      },
    });
    new Vue({
      el: "#app",
    });
    ```

  * `index.html`

    ```html
    <html>
      <head>
        <title>component app</title>
      </head>
      <body>
        <div id="app">
            <h1>Vue.js Components Fundamentals</h1>
            <click-counter></click-counter>
            <click-counter></click-counter>
            <click-counter></click-counter>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
        <script src="app.js"></script>
      </body>
    </html>
    ```

    * 컴포넌트는 재사용 가능해야 한다
    * 한 데이터에 대한 변화가 다른 데이터에 영향 주지 않는다

#### Component's Template

* template을 가지고 있는 `click-counter` 컴포넌트를 가진다

* 컴포넌트의 template을 정의하는 두 가지 방법이 있다

* 이 강의에서 `x-template`방법을 보여줌

  * 자바스크립트에서 string으로 template을 하는 대신에 HTML에 script element추가

  * `index.html`

    ```html
    ...
    	<div id="app">
          <h1>Vue.js Components Fundamentals</h1>
          <click-counter></click-counter>
          <click-counter></click-counter>
          <click-counter></click-counter>
        </div>
    
        <script type="text/x-template" id="click-counter-template">
          <button @click='click'>{{count}}</button>
        </script>
    ...
    ```

  * `app.js`

    ```javascript
    Vue.component("click-counter", {
      template: "#click-counter-template",
      data() {
        return {
          count: 0,
        };
      },
      methods: {
        click() {
          this.count++;
        },
      },
    });
    new Vue({
      el: "#app",
    });
    ```

* `Vue.js`에서 컴포넌트의 template은 단일 root element여야 한다

  ```html
  ...
  <script type="text/x-template" id="click-counter-template">
  	<div>
      	<p>the count is: </p>
          <button @click='click'>{{count}}</button>
      </div>
  </script>
  ..
  ```
  * 단일 root element 안에 있는 모든 element를 감싸야 한다

#### Reusable Components with props

components는 재사용가능하다고 여겨진다

click counter 컴포넌트의 다수의 instances를 사용하고 있지만, 더 현실적인 예시 없이는 재사용성의 이점을 이해하기 어렵다

커피 구독 서비스에 대한 페이지 렌더링

* `index.html`

  ```html
  <!DOCTYPE html>
  <html lang="en">
    <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <meta http-equiv="X-UA-Compatible" content="ie=edge" />
      <title>Vue.js Components Fundamentals</title>
    </head>
    <body>
      <div id="app" class="container mx-auto py-6">
        <div class="content">
          <h1 class="title">coffee Plans</h1>
  
          <h2 class="subtitle">
            we travel the world to source the very best single origin coffee for
            you
          </h2>
  
          <div class="plans">
            <div class="plan">
              <div class="description">
                <span class="title">
                  The Single
                </span>
              </div>
            </div>
  
            <div class="plan">
              <div class="description">
                <span class="title">
                  The Curious
                </span>
              </div>
            </div>
  
            <div class="plan">
              <div class="description">
                <span class="title">
                  The Addict
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
      <script src="app.js"></script>
      <link rel="stylesheet" href="style.css" />
    </body>
  </html>
  ```
* plan을 component로 만들어서 markup의 양을 줄이기

  * `app.js`

    ```javascript
    Vue.component("plan", {
      template: "#plan-template",
    });
    new Vue({
      el: "#app",
    });
    ```
    
  * `index.html`
  
      ```html
      ...
      <div class="plans">
           <plan></plan>
           ...
      </div>
      ...
          <script type="text/x-template" id="plan-template">
            <div class="plan">
              <div class="description">
                <span class="title">
                  The Addict
                </span>
              </div>
            </div>
          </script>
      ```
  
      


* component가 name을 받을 수 있도록, 컴포넌트에 name전달하기

  * `index.html`

    ```html
    <plan name="The Hacker"></plan>
    ```
    
    * 이것을 `prop`이라 한다
    * props: 컴포넌트에 등록할 수 있는 커스텀 속성
    
    * `app.js`
  
      ```javascript
      Vue.component("plan", {
        template: "#plan-template",
        props: ["name"],
      });
      ...
      ```

* props를 사용하기 위해, 컴포넌트가 받아들일 props리스트를 정의해야 한다
    * `index.html`

      ```html
      <script type="text/x-template" id="plan-template">
      	<div class="plan">
              <div class="description">
                	<span class="title">
                  	{{name}}
          		</span>
          	</div>
          </div>
      </script>
      ```
* markup에 plan의 이름을 하드코딩하는 대신에 array를 만들 수 있다

  * `app.js`

    ```javascript
    ...
    new Vue({
      el: "#app",
      data: {
        plans: ["The Hacker", "The Single", "The Curious", "The Addict"],
      },
    });
    ```

  * `index.html`

    ```html
    ...
    <div class="plans">
    	<plan v-for="plan in plans" :name="plan"></plan>
    </div>
    ...
    ```

    * v-for directive사용
    * plan의 값을 name속성에 bind하기 위해 `v-bind`를 사용해야 한다
      * shorthand: `:`사용 ex) `:name`

* props를 정의하는 또 다른 방법은 object(객체)를 사용하는 것이다

  * `app.js`

    ```javascript
    Vue.component("plan", {
      template: "#plan-template",
      props: {
        name: String,
        price: Number,
      },
    });
    ```

* prop의 값을 객체로 만들 수 있다

  * `app.js`

    ```javascript
    Vue.component("plan", {
      template: "#plan-template",
      props: {
        name: {
          type: String,
          // default: "Alex",
          required: true,
        },
        price: Number,
      },
    });
    ```

    * type의 key와 다른 가능성을 열수 있다
    * ex) default: default값, required: prop이 필수 인지 아닌지
    * 커스텀함 validation(유효성)규칙을 만들 수 있다

#### Nested Components

애플리케이션의 다른 장소에 plan을 보여주고 싶으면 Vue instance가 data에 plans 배열을 가지고 있는지 확인해야 한다

`plan picker`로 불리는 특정한 컴포넌트를 만듦으로써, plans 리스트를 더욱 재사용가능하게 만들 수 있다

* `index.html`

  ```html
  ...
  <div id="app" class="container mx-auto py-6">
      <div class="content">
          <h1 class="title">coffee Plans</h1>
  
          <h2 class="subtitle">
              we travel the world to source the very best single origin coffee for
              you
          </h2>
  
          <plan-picker></plan-picker>
      </div>
  </div>
  
  ...
  <script type="text/x-template" id="plan-picker-template">
  	<div class="plans">
          <plan v-for="plan in plans" :name="plan"></plan>
      </div>
  </script>
  ```

* `app.js`

  ```javascript
  ...
  
  Vue.component("plan-picker", {
    template: "#plan-picker-template",
    data() {
      return {
        plans: ["The Hacker", "The Single", "The Curious", "The Addict"],
      };
    },
  });
  
  new Vue({
    el: "#app",
  });
  ```

#### Global vs Local Components

컴포넌트를 등록하는 두 가지 방법이 있다: Globally(전역적) & Locally(지역적)

* 지금까지 `Vue.component`메서드만을 이용하여 컴포넌트를 등록해왔다-> **global registration**

  * 애플리케이션에서 전역으로 컴포넌트가 이용 가능

* 모든 컴포넌트를 전역으로 등록하고, Webpack과 같은 build system을 사용한다면, 컴포넌트를 그만 사용한다 할지라도, 그 컴포넌트가 final build에 여전히 포함되어 있음을 의미한다

  * 이것은 사용자가 다운로드 받아야 할  javascript의 양을 불필요하게 증가시킨다

* 몇 컴포넌트 경우에는 전역으로 등록될 필요가 없다

  * 예를 들어, plan picker의 외부에서 plan 컴포넌트를 쓸 필요가 없다

  * plan 컴포넌트를 javascript 객체로서 정의하고, 사용할 필요가 있는 곳에서 등록할 수 있다.

  * `app.js`

    ```javascript
    let PlanComponent = {
      template: "#plan-template",
      props: {
        name: {
          type: String,
          required: true,
        },
        price: Number,
      },
    };
    Vue.component("plan-picker", {
      template: "#plan-picker-template",
      components: {
        plan: PlanComponent,
      },
      data() {
        return {
          plans: ["The Hacker", "The Single", "The Curious", "The Addict"],
        };
      },
    });
    ```

    * 부모 컴포넌트인 PlanPicker내부에 Plan을 지역적으로 등록하기 위한 `components`컴포넌트 옵션을 사용한다
    * components는 object이다
      * key는 컴포넌트의 name(이름)
      * 값은 옵션 객체
    * plan 컴포넌트가 PlanPicker 내부에 지역적으로 등록->PlanPicker템플렛 외부에서 더 이상 사용x

* PlanPicker도 지역적으로 등록

  * `app.js`

    ```javascript
    let PlanPickerComponent = {
      template: "#plan-picker-template",
      components: {
        plan: PlanComponent,
      },
      data() {
        return {
          plans: ["The Hacker", "The Single", "The Curious", "The Addict"],
        };
      },
    };
    new Vue({
      el: "#app",
      components: {
        "plan-picker": PlanPickerComponent,
      },
    });
    ```

* 요약

  * 필수적인 컴포넌트만 전역으로 등록
    * ex) BaseButton, input등
  * 그 외 나머지는 지역적으로 등록

#### Communication Between Components with Custom Events

중첩된 컴포넌트를 갖고 있다면, 컴포넌트 사이에서 대화하고 데이터를 전달할 방법이 필요하다

props를 사용하여 자식(child)컴포넌트로 데이터를 전달하는 것을 알고 있지만, 자식에서 부모로 데이터를 전달하는 방법은 모른다

자식에서 부모로 데이터를 전달하는 방법을 알아보자

* plan picker에서 plan을 고르기를 원한다

  * `app.js`

    ```javascript
    let PlanComponent = {
      template: "#plan-template",
      ...
      data() {
        return {
          selected: false,
        };
      },
      methods: {
        select() {
          this.selected = true;
        },
      },
    };
    ```

    * 컴포넌트에 plan이 선택되었는지 아닌지 정의하기 위해 data property 추가
    * selected data를 업데이트 하기 위한 메서드 필요

  * `index.html`

    ```html
    ...
    <script type="text/x-template" id="plan-template">
    	<div @click="select" class="plan" :class="{'active-plan':selected}">
            <div class="description">
              <span class="title">
                {{name}}
              </span>
            </div>
     	</div>
    </script>
    ...
    ```

    * 사용자가 plan을 클릭할 때 select 메서드를 호출한다
    * plan이 선택될 때, 동적으로 active-plan클래스를 추가하기 위해 `class-binding`을 사용한다

* 오직 한 개의 plan만 선택될 수 있게 하고 싶음

  * 이전에 선택된 plan을 unselecte되도록 select메서드를 만들어야 함 
  * 만약 첫 번째 플랜을 선택하고 두 번째를 클릭하면, 첫 번째 플랜은 더 이상 선택되어지면 안됨
  * 어떻게 컴포넌트 내부에서 다른 plan이 선택되어 있는지를 알 수 있는가?
    * props를 사용할 수 있지만, 부모는 plan이 선택되었는지 아닌지 인지할 수 없다
    * 부모가 사용자가 plan을 선택하는 때를 알게 해야 함
    * 이를 위해 커스텀 이벤트를 사용할 것이다

* 커스텀 이벤트를 전달하기 위해 `$emit`메서드를 사용

  * `app.js`

    ```javascript
    let PlanComponent = {
      template: "#plan-template",
      props: {
        name: {
          type: String,
          required: true,
        },
        price: Number,
      },
      data() {
        return {
          selected: false,
        };
      },
      methods: {
        select() {
          this.$emit("select", this.name);
          this.selected = true;
        },
      },
    };
    ```

    * `$emit`의 첫 번째 인자는 발송하기 원하는 이벤트의 이름
      * 이벤트의 이름은 무엇이던 될 수 있음
      * 모든 전송된(emitted) 이벤트는 vue devtool의 event tab으로 볼 수 있다
    * `$emit`의 두 번째 인자는 event로 전달하고 싶은 데이터이다(optional)
      * 부모가 어떤 plan이 선택되었는지 알 수 있도록 이벤트의 이름 전달
    * 이벤트 데이터는 종종 `payload`라고 불린다

* click 또는 keydown과 같은 DOM event와 같이 `v-on`을 사용해서 parent인 plan-picker에서 select event를 들을 수 있다

  * `index.html`

    ```html
    ...
          <div class="plans">
            <plan v-for="plan in plans" :name="plan" @select="selectPlan"></plan>
          </div>
    ...
    ```

    * select 이벤트가 발생할 때, selectPlan메서드를 동작시킨다

  * plan-picker 컴포넌트는 어떤 plan이 선택돼야 하는지 알 필요가 있다

    * 이것을 저장하기 위해, data property, selectedPlan을 추가한다

    ```javascript
    <!--app.js-->
    ...
    let PlanPickerComponent = {
      template: "#plan-picker-template",
      components: {
        plan: PlanComponent,
      },
      data() {
        return {
          plans: ["The Hacker", "The Single", "The Curious", "The Addict"],
          selectedPlan: null,
        };
      },
      methods: {
        selectPlan(plan) {
          this.selectedPlan = plan;
        },
      },
    };
    ```

    * plan을 받기 위한 `selectPlan`메서드도 추가

  * parent가 어떤 plan이 선택되었는지 알 수 있으므로 plan컴포넌트의 selected data를 제거하고, prop을 추가

    ```javascript
    let PlanComponent = {
      template: "#plan-template",
      props: {
        name: {
          type: String,
          required: true,
        },
        selectedPlan:{
            type:String,  
        }
      },
      methods: {
        select() {
          this.$emit("select", this.name);
        },
      },
    };
    ...
    ```

    * 언제 active plan이 선택되는지 알 필요가 있으므로 이 class를 동적으로 추가한다

* template에서 selectedPlan과 현재 plan이 같은지 확인할 수 있을 뿐 아니라, `computed` property를 사용할 수도 있다

  * 컴포넌트는 vue instance가 할 수 있는 무엇이든지 간에 할 수 있기 때문

* `isSelected`라고 불리는 `computed` property를 만들 것이다

  * `app.js`

    ```javascript
    let PlanComponent = {
      template: "#plan-template",
      props: {
        name: {
          type: String,
          required: true,
        },
        selectedPlan: {
          type: String,
        },
      },
      computed: {
         isSelected(){
             return this.name === this.selectedPlan
         }
      },
      methods: {
        select() {
          this.$emit("select", this.name);
        },
      },
    };
    ....
    ```

    * computed property는 true or false리턴

* 새로운 computed property를 사용해서 class binding update

  * `index.html`

    ```html
        <script type="text/x-template" id="plan-template">
          <div @click="select" class="plan" :class="{'active-plan':isSelected}">
            <div class="description">
              <span class="title">
                {{name}}
              </span>
            </div>
          </div>
        </script>
    ```

* 컴포넌트에 selectedPlan 전달

  * `index.html`

    ```html
        <script type="text/x-template" id="plan-picker-template">
          <div class="plans">
            <plan v-for="plan in plans" :name="plan" @select="selectPlan" :selected-plan="selectedPlan"></plan>
          </div>
        </script>
    ```

    * `:selected-plan="selectedPlan"`추가
    * 사용자는 하나의 plan만 선택 가능하고 plan picker는 어떤 plan이 선택되었는지 안다



### 2. Componets In-depth

#### Component Namin Best Practices

컴포넌트에 이름을 붙이는 것에 관해서, 점점 더 복잡해질 수 있다.  그래서, vue team이 컴포넌트 이름을 짓는데 도움을 주기 위한 style 가이드를 준다

규칙은 중요도를 기반으로한 section으로 나누어진다

* 대부분의 규칙에서 아직 둘러싸이지 않은 것은 단일 파일 컴포넌트를 사용한다

**우선 순위**

1. Muti-word 컴포넌트 이름

   * 컴포넌트는 항상 root app 컴포넌트를 제외하고 `multi-word`여야 한다

   * 모든 html element는 single word이므로 존재하는 html element와 미래의 html element가 충돌하는 것을 막아줌

     * ex) button, table, textarea etc.

   * `todo-item`과 같이 이름을 지어줘야 함

   * `Plan` 컴포넌트를 `PlanItem`로 이름 다시 짓기

     * `app.js`

       ```javascript
       let PlanItemComponent = {
           ..
       }
       ...
       let PlanPickerComponent = {
         template: "#plan-picker-template",
         components: {
           "plan-item": PlanItemComponent,
         },
       ...
       ```

       * 컴포넌트를 PlanPicker내부에 지역적으로 등록했기 때문에 property이름도 역시 컴포넌트의 이름이 되야 한다

     * `index.html`

       ```html
       ...
       <script type="text/x-template" id="plan-picker-template">
             <div class="plans">
               <plan-item v-for="plan in plans" :name="plan" @select="selectPlan" :selected-plan="selectedPlan"></plan-item>
           </div>
       </script>
       ....
       ```

2. Base 컴포넌트 이름을 가진다

   * Base component는 앱의 특정한 스타일링과 convention 을 적용한 것
   * 애플리케이션에서 전역으로 사용되는 것 ex) button, input
   * 컴포넌트의 이름은 prefix(접두사)로 시작 ex) Base, App, or V
   
3.  Single-instance 컴포넌트 이름

   * 이러한 컴포넌트는 오직 단일 인스턴스 active를 가지고, `The`로 시작해야 한다
   * 예를 들어, 웹사이트는 주어진 페이지에 오직 하나의 sidebar를 가지므로 그 컴포넌트를 `TheSideBar`로 이름 붙일 수 있다

4. Tightly coupled 컴포넌트 이름

   * 부모 컴포넌트의 context에서, 관계가 그 이름에서 분명해야 한다

   * 에디터가 파일을 알파벳 순서대로 정렬하기 때문에,  이것은 관련된 파일을 서로서로 옆에 둘 수 있도록 한다.

   * 예를 들어, 여기에 몇 개의 나쁜 컴포넌트 이름이 있다

     * todo list, todo item, todo button
     * 이러한 이름이 위험해 보이지 않을지 모르지만, 실제로는 꽤 나쁘다
     * 이러한 컴포넌트를 규칙을 따르도록 하기 위해서, 관계가 이름에서 명료하게 만들 필요가 있다.

   * TodoList, TodoListITem, TodoListItemButton

     * 얼핏 보면 장황해 보일 수 있지만, 생각해보면 완전히 타당하다
     * TodoListItemButton은 TodoListItem 컴포넌트 밖에서는 절대 사용되지 않을 것이다.

   * PlanItem 컴포넌트는 PlanPicker내부에 있어야만 말이 된다.

     * 더 명확히 하기 위해 PlanItem대신에 PlanPickerItem이라고 불러야 한다

     * `app.js`

       ```javascript
       let PlanPickerItemComponent = {
           template: "#plan-picker-item-template",
           ..
       }
       ...
       let PlanPickerComponent = {
         template: "#plan-picker-template",
         components: {
           "plan-picker-item": PlanPickerItemComponent,
         },
           ...
       }
       ```

     * `index.html`에서 새로운 컴포넌트 이름을 반영하기 위해 template id를 업데이트

       ```html
       ...
       <script type="text/x-template" id="plan-picker-template">
       	<div class="plans">
               <plan-picker-item v-for="plan in plans" :name="plan" @select="selectPlan" :selected-plan="selectedPlan"></plan-picker-item>
            </div>
       </script>
       <script type="text/x-template" id="plan-picker-item-template">
        ..
       </script>
       ```

5. 컴포넌트 이름에서 단어의 순서(Order of words in component name)

   * 컴포넌트 이름은 가장 일반적인 단어로 시작해야 하고, 묘사하는 수정하는 (descriptive modifying words)단어로 끝나야 한다
   * 나쁜 예
     * 검색에 관련된 컴포넌트 리스트가 있다
       * ClearSearchButton, RunSearchButton등
       * 가장 일반적인 단어는 word Search이다. 이것은 컴포넌트 그 자체를 묘사하지 않지만 사용되는  일반적인 기능 때문에 
       * 이 말은 이러한 이름은 Search로 시작해야 함을 의미한다
       * `SearchButtonClear`, `SearchButtonRun`과 같이 변경해야 함
     * Setting과 관련된 두 개의 컴포넌트가 있다
       * TermsCheckbox, LaunchOnStartupCheckbox
       * 이 컴포넌트를 적절하게 이름붙일 때, 둘 다 Setting으로 시작해야 하고, 두 번째는 Checkbox와 같은 가장 일반적인 단어로 해야 한다.
       * 이 컴포넌트는 결론적으로 `SettingsCheckboxTerms`,`SettingsCheckboxLaunchOnStartup`이 된다

6. Full-word component names

   * 축약형이나 약칭 대신에 full words로 사용하는 것을 추천한다
   * SdSettings와 같이 컴포넌트 이름을 짓는 것 대신에 StudentDashboardSettings와 같이 이름 지어야 한다.

#### Component Lifecycle Hooks

각각 Vue 인스턴스는 생성될 때, 일련의 초기화 단계를 거친다.

예를 들어, 데이터 관측(data observation)을 셋업, DOM에 인스턴스 마운트하는 등등을 할 필요가 있다

그 과정에서, 특정한 단계에서 코드를 추가할 수 있는 기회를 제공하는 lifecycle hooks라고 불리는 함수들을 실행시킨다

다른 말로 하면, 필요로 하는 컴포넌트 라이프사이클의 어느 단계에서던지 코드를 실행시킬 수 있다

* 예를 들어, BlogPost 컴포넌트가 있고, API로부터 post를 가져올(fetch)할 필요가 있다고 하자

  * 이러한 것을 생성된 lifecyle hook에서 할 수 있다

  * BlogPost 컴포넌트 만들기

    ```javascript
    //lifecycle-hook-example.js
    let BlogPostComponent = {
      props: ["id"],
      data() {
        return {
          blogPost: null,
        };
      },
      created() {
        axios.get("api/posts/" + this.id).then((response) => {
          this.blogPost = response.data;
        });
      },
    };
    ```

    * prop에 post의 id를 가진다
    * API에서 실제 blog post를 가져오길 원한다(fetch)
    * 컴포넌트가 생성될 때, 코드가 실행되길 원한다. 그래서 created hook를 사용할 것이다.
    * hooks는 data와 같은 함수이다
      *  hook function내부에서  컴포넌트 인스턴스에 접근한다
      * post의 id를 가지고 있고, prop에 들어 있기를 원함
    * axios를 사용
      * api url을 요청한다
      * response를 받는다

#### Component Slots

지금까지 컴포넌트에 데이터를 넘기기 위해 props를 사용해 왔다. slot을 사용해서 컴포넌트에 컨텐트(content)를 분배하는 또 다른 방법이 있다

* Slot은 Vue.js의 멋진 특징이며, 다른 코스에서 이것을 깊이 다룰 것이다

* slot이 어떻게 동작하는가?

  * slot을 사용하면, 이와 같이 컴포넌트를 사용할 수 있다 

  * `index.html`

    ```html
    <!DOCTYPE html>
    <html lang="en">
      <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Vue.js slot Fundamentals</title>
      </head>
      <body>
        <div id="app">
          <h1>Vue.js Slots Fundamentals</h1>
          <todo-item>
            Buy Bananas
          </todo-item>
        </div>
    
        <script src="https://unpkg.com/vue"></script>
        <script src="app.js"></script>
      </body>
    </html>
    ```

    * props를 사용하여 컴포넌트에 item을 전달하는 대신에, 컴포넌트 element를 사용한다

  * `app.js`

    ```javascript
    Vue.component("todo-item", {
      template: "#todo-item-template",
    });
    new Vue({
      el: "#app",
    });
    ```

    * todo-item 컴포넌트를 만든다
    * slot에서 전달되는 content를 보여줘야 한다
    * 컴포넌트에 template을 할당한다.
    
  * `index.html`
  
    ```html
    ...
        <script type="text/x-template" id="todo-item-template">
          <div>
              <slot></slot>
          </div>
        </script>
    ...
    ```
  
    * 템플릿 안에, slot을 정의하기 위해 slot element사용
    * 실행하면, Buy bananas todo를 브라우저에서 볼 수 있음
    * 컴포넌트 템플릿에 slot에 따라 어떠한 element든 포함할 수 있음
  
* 위의 예제에서 slot전에 todo의 status를 toggle하는(토글키로 켰다 껐다 하는) checkbox추가

  * `index.html`

    ```html
    ...
    <script type="text/x-template" id="todo-item-template">
    	<div>
        	<input type="checkbox">
        	<slot></slot>
        </div>
    </script>
    ...
    ```

* status를 포함할 수 있는 data  property추가

  * `app.js`

    ```javascript
    Vue.component("todo-item", {
      template: "#todo-item-template",
      data() {
        return {
          completed: false,
        };
      },
    });
    ...
    ```

* v-model을 사용하여 checkbox에 completed를 bind한다

  * `index.html`

    ```html
    ...
    <script type="text/x-template" id="todo-item-template">
    	<div>
              <input type="checkbox" v-model="completed">
              <slot></slot>
        </div>
    </script>
    ...
    ```

* multiple slot을 사용하기 원함

  * `index.html`

    ```html
      <body>
        <div id="app">
          <h1>Vue.js Slots Fundamentals</h1>
          <todo-item>
            Buy Bananas
            <p>
              Bananas are good for your healths
            </p>
          </todo-item>
        </div>
    
        <script type="text/x-template" id="todo-item-template">
          <div>
              <input type="checkbox" v-model="completed">
              <span :class="{done: completed}">
                <slot></slot>
              </span>
              <slot name="description" ></slot>
          </div>
        </script>
        <script src="https://unpkg.com/vue"></script>
        <script src="app.js"></script>
        <style>
          .done {
            color: green;
            text-decoration: line-through;
          }
        </style>
      </body>
    ```

    * slot element는 특별한 attribute(속성)을 가짐: name
      * 추가 slot을 정의하는데 사용됨
    * todo-item element으로 넣어둘 content가 무엇이던지 간에 default slot으로 렌더링될 것이다
      * 만약 description을 위에 와 같이 썼을 때,  span에서 렌더링 될 것이고, todo가 done이 됐을 때, 그 description은 이름과 같은 스타일링을 얻게 될 것이다 

* content에 description slot을 전달하기 위해서, template element에 `v-slot`directive를 사용할 수 있다

  * `index.html`

    ```html
    ...
          <todo-item>
            Buy Bananas
            <template v-slot:description>
              <p>
                Bananas are good for your healths
              </p>
            </template>
          </todo-item>
    ...
    ```

    * `v-slot`directive: v-slot의 인자로서 slot의 이름을 제공 

    * 실행해보면, todo가 done으로 체크하면 description은 건드리지 않는 상태로 유지된다

    * shorthand를 좋아한다면, `v-slot`대신에 `#`기호를 사용할 수 있다

      ```html
      <template #description>
      ```

* v-slot문법은 Vue 2.6.0부터 사용가능

* Slot은 컴포넌트의 element를 컨트롤하기 원할때 매우 간편하다

  * 예를 들어, todo item은 todo를 하이라이트하는 button을 가질 수 있는데, props를 사용하는 대신에 text를 slot으로 만들 수 있다

  * `index.html`

    ```html
    ...
          <todo-item>
            ...
            <template #button-text>
              make it rain
            </template>
          </todo-item>
    ...
    <script type="text/x-template" id="todo-item-template">
          <div>
          	<input type="checkbox" v-model="completed">
              <span :class="{done: completed}">
                <slot></slot>
        		</span>
              <slot name="description" ></slot>
              <button>
                  <slot name="button-text"></slot>
        	  </button>
        </div>
    </script>
    ```

* button text가 없는 또 다른 todo 추가

  * `index.html`

    ```html
    ...
    	<div id="app">
          <h1>Vue.js Slots Fundamentals</h1>
          <todo-item>
            Buy Bananas
            <template #description>
              <p>
                Bananas are good for your health
              </p>
            </template>
            <template #button-text>
              make it rain
            </template>
          </todo-item>
          <todo-item>
            Eat Bananas
            <template #description>
              <p>
                Bananas are good
              </p>
            </template>
          </todo-item>
        </div>
    ...
    ```

    * 비어있는 버튼을 갖게 됨. 이것은 slot을 요구하게 함

* button-text slot을 optional하게 하기 위해서, 디폴트 값을 줄 수 있다

  * `index.html`

    ```html
    ...
    	<script type="text/x-template" id="todo-item-template">
          <div>
              ...
              <slot name="description" ></slot>
              <button>
                  <slot name="button-text">Highlight</slot>
              </button>
          </div>
        </script>
    ...
    ```

    * slot element안에 default content를 추가하면 된다
    * button-text가 제공되면,  slot element가 교체되고 button-text가 제공되지 않는 경우에는 default content가 보여진다

* HTML나 markup을 컴포넌트에 전달할 필요가 있을 때는 slot이 좋다

  data를 컴포넌트에 전달할 필요가 있을 때는 props가 적격이다

### 3. Vue.js Component Exercises

#### Build a GitHub User Profile Component

#### Create a Notification Message Component

## Vuex for Everyone

> https://vueschool.io/courses/vuex-for-everyone

