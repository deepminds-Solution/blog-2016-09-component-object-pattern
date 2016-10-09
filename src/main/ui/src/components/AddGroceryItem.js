import React, { PropTypes } from 'react';
import { connect } from 'react-redux';
import { addGroceryItem } from '../actions';

const AddGroceryItem = ({ dispatch }) => {
  let input;

  return (
    <div>
      <form
        onSubmit={e => {
          e.preventDefault();
          if (!input.value.trim()) {
            return;
          }
          dispatch(addGroceryItem(input.value));
          input.value = '';
        }}
      >
        <input ref={node => { input = node; }} />
        <button type="submit">
          Add Grocery Item
        </button>
      </form>
    </div>
  );
};

AddGroceryItem.propTypes = {
  dispatch: PropTypes.func.isRequired,
  className: PropTypes.func.string,
};

export default connect()(AddGroceryItem);
