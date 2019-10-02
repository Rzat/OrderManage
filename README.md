# OrderManage



when you will run the application, the database will be loaded with the two default customers with two default orders each.


    below are the API's endpoints list and description
	1) Create new Customer= http://localhost:8040/api/v1/customers
			json request for api:  		{
											"firstName":"Tom",
											"lastName":"Brew"
										}
										
			json expected Response:  
										{
											"firstName":"Tom",
											"lastName":"Brew"
										}
	
	
	2) for creating an Order for particular customer = http://localhost:8040/api/v1/customers/createOrder
			json request for api:  		{
											"customerId":2,
											"description":"order20"
										}
										
			json expected Response:  
										{
											"id": 4,
											"description": "order20"
										}
			json expected response for 10 Orders:
										{
											"id": 13,
											"description": "Order10",
											"discountAppliedInPercent": 10
										}
			
			json expected response for 20 Orders:
										{
											"id": 13,
											"description": "Order20",
											"discountAppliedInPercent": 20
										}			
								
	
	3) get Order by Customer By Id = http://localhost:8040/api/v1/customers/3
			json expected Response:  
										{
											"firstName":"Tom",
											"lastName":"Brew"
										}
	
		some sample response for get Order By Customer Id
										{
											"id": 3,
											"firstName": "Tom",
											"lastName": "Brew",
											"category": "Gold",
											"discountInPercent": 10,
											"totalDiscountGiven": 20
										}
										
										
										{
                                        	"id": 4,
                                        	"firstName": "Mum",
                                        	"lastName": "Brew",
                                        	"category": "Platinum",
                                        	"discountInPercent": 20,
                                        	"totalDiscountGiven": 40
                                        }
	
	4) get selected Order By CustomerIdOrderId = http://localhost:8040/api/v1/customers/3/ingredients/5/show
			json expected Response:  
										{
											"id":5,
											"description": "order5"
										}

	5)get all orders of a particular customer = http://localhost:8040/customer/1
			json expected Response:  
										[
											{
												"id": 1,
												"description": "FirstOrder"
											},
											{
												"id": 2,
												"description": "SecondOrder"
											}
										]


	
		