/**
 * QnA modify 수정 삭제
 */

 document.addEventListener('DOMContentLoaded', () => {
    
    const modifyForm = document.querySelector('#qnaModifyForm')
    
    
    // 수정 업데이트
    const btnUpdate = document.querySelector('#btnUpdate')
    btnUpdate.addEventListener('click', (e) => {
        const title= document.querySelector('input#title').value;
        const content = document.querySelector('textarea#content').value;
        if (title === '' || content === '') {
            alert('제목과 내용은 꼭 입력해주세요');
            return; 
        }
        
        const check = confirm('업데이트?');
        if(check) {
            
            
            modifyForm.action = '/info/qna/update'
            modifyForm.method = 'post';   
            modifyForm.submit(); 
        }
    });
    
    // 삭제
    const btnDelete = document.querySelector('#btnDelete');
    btnDelete.addEventListener('click', (e) => {
        const result = confirm('삭제?');
        if (!result) {
            return;           
        } 
          
            modifyForm.action = '/info/qna/delete' 
            modifyForm.method = 'post';  
			modifyForm.submit(); 
    });
    
    
    
});