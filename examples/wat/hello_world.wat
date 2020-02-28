(module
  (func $getAnswer (result i32)
    i32.const 1)
  (func (export "main") (result i32)
    call $getAnswer
    i32.const 1
    i32.sub))
