import React from 'react';
import Footer from './Footer';
import AddTodo from './AddTodo';
import VisibleTodoList from './VisibleTodoList';
import AddGroceryItem from './AddGroceryItem';
import VisibleGroceryList from './VisibleGroceryList';

const App = () => (
  <div>
    <AddTodo />
    <VisibleTodoList />
    <AddGroceryItem />
    <VisibleGroceryList />
    <Footer />
  </div>
);

export default App;
