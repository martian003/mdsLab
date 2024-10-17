//to connect to blockchain with front end
App={
    web3Provider: null, 
    contracts: {},
    //whenever you open index.html,this function is called
    init: async function(){
        return await App.initWeb3();
    },
    //initWeb3 is called by init function to initialise web3 provider
    initWeb3: async function() {
        // Check for MetaMask (window.ethereum)
        if (window.ethereum) {
            App.web3Provider = window.ethereum;
            try {
                // Request account access
                await window.ethereum.request({ method: "eth_requestAccounts" });
            } catch (error) {
                // User denied account access
                console.error("User denied account access");
            }
        } else if (window.web3) {
            App.web3Provider = window.web3.currentProvider;
        } else {
            // Fallback to Ganache
            App.web3Provider = new Web3.providers.HttpProvider('http://127.0.0.1:7545');
        }
        web3 = new Web3(App.web3Provider);
        return App.initContract();
    },
    //initContract is called by initWeb3 to initialise the contract
    initContract:async function(){
        //specify the artifact
        $.getJSON('voting.json',function(data){
            var votingArtifact=data;
            App.contracts.voting=TruffleContract(votingArtifact);//set contract 
            App.contracts.voting.setProvider(App.web3Provider);//set metamask
            
             return App.displayVotes();
        });
        return App.bindEvents();
    },
    //bindEvents is called by initContract
    bindEvents:function(){
        $(document).on('click','.btn-vote',App.handleVote);
    },
    //displayVotes is called by initContract
    //this function is displaying votes on the front end
    displayVotes:function(){
        var votingInstance;
        //connect with contract ->that instance we have to store
        App.contracts.voting.deployed().then(function(instance){
            votingInstance=instance;//instance is stored
            return votingInstance.viewVotes.call();//calling the solidity function
        }).then(function(votes){//votes of 3 leaders
            document.getElementById('v1').innerHTML=votes[0]['c'][0];
            document.getElementById('v2').innerHTML=votes[1]['c'][0];
            document.getElementById('v3').innerHTML=votes[2]['c'][0];

        }).catch(function(err){
            console.log(err.message);
        })
    },
    handleVote: async function(event) {
        event.preventDefault();
    
        const id = parseInt($(event.target).attr('data-id'));  // Correct data-id
        console.log("Voted for ID: ", id);
    
        // Request account from MetaMask
        try {
            const accounts = await window.ethereum.request({ method: 'eth_requestAccounts' });
            const account = accounts[0];
            console.log("Using account: ", account);
    
            const votingInstance = await App.contracts.voting.deployed();
            const result = await votingInstance.castVote(id, { from: account });
    
            console.log("Vote result: ", result);
            document.getElementById('add').innerHTML = account;
            return App.displayVotes();
        } catch (err) {
            console.error(err.message);
        }
    }
    
};
//whenever you open index.html-App.init() will be called
$(function(){
    $(window).on('load',function(){
        App.init();
    });
});