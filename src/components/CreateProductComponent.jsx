import React, { Component } from 'react'
import ProductService from '../services/ProductService';

class CreateProductComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            // step 2
            id: this.props.match.params.id,
            pName: '',
            loc: ''
        }
        this.changepNameHandler = this.changepNameHandler.bind(this);
        this.changelocHandler = this.changelocHandler.bind(this);
        this.saveOrUpdateProduct = this.saveOrUpdateProduct.bind(this);
    }

    // step 3
    componentDidMount(){

        // step 4
        if(this.state.id === '_add'){
            return
        }else{
            ProductService.getProductById(this.state.id).then( (res) =>{
                let product = res.data;
                this.setState({pName: product.pName,
                    loc: product.loc
                   
                });
            });
        }        
    }
    saveOrUpdateProduct = (e) => {
        e.preventDefault();
        let product = {pName: this.state.pName, loc: this.state.loc};
        console.log('product => ' + JSON.stringify(product));

        // step 5
        if(this.state.id === '_add'){
            ProductService.createProduct(product).then(res =>{
                this.props.history.push('/products');
            });
        }else{
            ProductService.updateProduct(product, this.state.id).then( res => {
                this.props.history.push('/products');
            });
        }
    }
    
    changepNameHandler= (event) => {
        this.setState({pName: event.target.value});
    }

    changelocHandler= (event) => {
        this.setState({loc: event.target.value});
    }

    

    cancel(){
        this.props.history.push('/products');
    }

    getTitle(){
        if(this.state.id === '_add'){
            return <h3 className="text-center">Add Product</h3>
        }else{
            return <h3 className="text-center">Update Product</h3>
        }
    }
    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                {
                                    this.getTitle()
                                }
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> Product Name: </label>
                                            <input placeholder="Product Name" name="pName" className="form-control" 
                                                value={this.state.pName} onChange={this.changepNameHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Location: </label>
                                            <input placeholder="Location" name="loc" className="form-control" 
                                                value={this.state.loc} onChange={this.changelocHandler}/>
                                        </div>
                                        

                                        <button className="btn btn-success" onClick={this.saveOrUpdateProduct}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                   </div>
            </div>
        )
    }
}

export default CreateProductComponent