import { useRef } from "react"; 
import { FaBars, FaTimes } from "react-icons/fa"; 
import { useState } from "react"; 
import "../Styles/Comments.css";

const Comments = () =>{


    const [text, setText] = useState('');
  const [texts, setTexts] = useState([]);
  const [editIndex, setEditIndex] = useState(-1);
  const [editText, setEditText] = useState('');

  function handleChange(event) {
    const newValue = event.target.value;
    setText(newValue);
  }

  function handleSubmit(event) {
    event.preventDefault();
    if (editIndex !== -1) {
      const newtexts = [...texts];
      newtexts[editIndex] = editText;
      setTexts(newtexts);
      setEditIndex(-1);
      setEditText('');
    } else {
      setTexts([...texts, text]);
      setText('');
    }
  }

  function handleDelete(index) {
    const newtexts = [...texts];
    newtexts.splice(index, 1);
    setTexts(newtexts);
  }

  function handleEdit(index) {
    setEditIndex(index);
    setEditText(texts[index]);
  }

  function handleCancel() {
    setEditIndex(-1);
    setEditText('');
  }

  return (
    <div>
      <form onSubmit={handleSubmit} class = "enter">
        <label htmlFor="textfield">Enter some text:</label>
        <input type="text" id="textfield" value={editIndex !== -1 ? editText : text} onChange={handleChange} />
        <button type="submit" class = "enter">{editIndex !== -1 ? 'Update' : 'Submit'}</button>
        {editIndex !== -1 && <button type="button" onClick={handleCancel}>Cancel</button>}
      </form>
      <table class = "comment">
        <thead>
          <tr>
            <th>Comments&nbsp;</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {texts.map((t, index) => (
            <tr key={index}>
              <td>{editIndex === index ? <input type="text" value={editText} onChange={(e) => setEditText(e.target.value)} /> : t}</td>
              <td>
                {editIndex === index ? (
                  <>
                    <button onClick={() => handleSubmit({ preventDefault: () => {} })}>Save</button>
                    <button onClick={handleCancel}>Cancel</button>
                  </>
                ) : (
                  <>
                    <button onClick={() => handleEdit(index)}>Edit</button>
                    <button onClick={() => handleDelete(index)}>Delete</button>
                  </>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Comments; 