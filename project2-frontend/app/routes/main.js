import Route from '@ember/routing/route';

export default Route.extend({
    model() {
        return this.store.peekAll('login');

    },

    init:function(){
        this.refresh
    },

    actions: {
        login(username, password){
            console.log(username);
            console.log(password);
            console.log("made it into login action");
        }
    }
    
});
