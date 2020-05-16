import axios from "axios";

const initialState = {
    authOk: false,
    data: []
}


const reducer = (state = initialState, action) => {
    switch (action.type) {
        case 'VERIFY_AUTH':
            console.log("verify auth")
            console.log(state.authOk)
            return {
                ...state,
                authOk:  action.authOk
            }

        case 'LOGOUT':
            console.log("Dispatch logout")
            return {
                ...state,
                authOk: false
            }
        case 'SAVE_DATA':
            console.log('Save data action')
            console.log('Data in action: ' + action.data)
            return {
                ...state,
                data: action.data
            }
    }
    return state;
}
    
export default reducer;